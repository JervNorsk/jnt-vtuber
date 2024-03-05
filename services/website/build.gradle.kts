import org.jetbrains.kotlin.cli.common.isWindows
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
  // Kotlin
  // ------------------------------------------------------------------------
  kotlin("multiplatform")

  // Kotlin Plugins
  // ------------------------------------------------------------------------
  kotlin("plugin.serialization")
}

kotlin {
  js {
    nodejs()
  }
  sourceSets {
    val jsMain by getting {
      resources.srcDirs(
        "src/jsMain/typescript",
        rootProject.layout.buildDirectory
          .get()
          .dir("js/packages/${rootProject.name}-${project.name}/node_modules")
          .asFile
          .path
      )
      dependencies {
        implementation(devNpm("@angular/cli", "^16.1.0"))
        implementation(devNpm("@angular-devkit/build-angular", "^16.1.0"))
        implementation(devNpm("@angular/compiler-cli", "^16.1.0"))
        implementation(devNpm("typescript", "5.1.3"))

        implementation(npm("@angular/core", "^16.1.0"))
        implementation(npm("@angular/common", "^16.1.0"))
        implementation(npm("@angular/compiler", "^16.1.0"))
        implementation(npm("@angular/platform-browser", "^16.1.0"))
        implementation(npm("@angular/platform-browser-dynamic", "^16.1.0"))
        implementation(npm("@angular/router", "^16.1.0"))
        implementation(npm("rxjs", "^6.5.3"))
        implementation(npm("zone.js", "~0.13.0"))

//        implementation(npm("@angular/animations", "^17.1.0"))
//        implementation(npm("@angular/common", "^17.1.0"))
//        implementation(npm("@angular/compiler", "^17.1.0"))
//        implementation(npm("@angular/forms", "^17.1.0"))
//        implementation(npm("@angular/platform-browser", "^17.1.0"))
//        implementation(npm("@angular/platform-browser-dynamic", "^17.1.0"))
//        implementation(npm("@angular/router", "^17.1.0"))
//        implementation(npm("tslib", "~2.3.0"))
      }
    }
  }
  tasks {
    // TODO: set generated source directory to node_modules in build folder
    create("ngCopyConfiguration", Copy::class) {
      dependsOn("build")
      from(projectDir)
      into(
        rootProject.layout.buildDirectory
          .get()
          .dir("js/packages/${rootProject.name}-${project.name}")
          .asFile
      )
      include(
        "angular.json",
        "tsconfig*.json"
      )
    }
    create("ngCopyResources", Copy::class) {
      dependsOn("build")
      from(projectDir)
      into(
        rootProject.layout.buildDirectory
          .get()
          .dir("js/packages/${rootProject.name}-${project.name}")
          .asFile
      )
      include("src/*/resources/**/*.*")
    }
    create("ngCopySources", Copy::class) {
      dependsOn("build")
      from(projectDir)
      into(
        rootProject.layout.buildDirectory
          .get()
          .dir("js/packages/${rootProject.name}-${project.name}")
          .asFile
      )
      include("src/*/typescript/**/*.*")
    }
    create("ngUpdateResources") {
      dependsOn(
        "ngCopyConfiguration",
        "ngCopyResources",
        "ngCopySources"
      )
    }
    create("ngBuild", Exec::class) {
      dependsOn("ngUpdateResources")
      mutableListOf<String>(
        "npx"
      ).also {
        if (isWindows) {
          it.add(0, "cmd")
          it.add(1, "/c")
        }
      }.also {
        it += "-w"
        it += "packages\\${rootProject.name}-${project.name}"
        workingDir =
          rootProject.layout.buildDirectory
            .get()
            .dir("js")
            .asFile
      }.also {
        it += "ng"
        it += "build"
      }.also {
        println(it)
        commandLine(it)
      }
    }
    create("ngServe", Exec::class) {
      dependsOn("ngUpdateResources")
      mutableListOf<String>(
        "npx"
      ).also {
        if (isWindows) {
          it.add(0, "cmd")
          it.add(1, "/c")
        }
      }.also {
        it += "-w"
        it += "packages\\${rootProject.name}-${project.name}"
        workingDir =
          rootProject.layout.buildDirectory
            .get()
            .dir("js")
            .asFile
      }.also {
        it += "ng"
        it += "serve"
      }.also {
        println(it)
        commandLine(it)
      }
    }
  }
}
