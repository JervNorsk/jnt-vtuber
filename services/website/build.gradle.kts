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
      kotlin {
        srcDir("src/jsMain/typescript")
      }
      dependencies {
        implementation(devNpm("@angular/cli", "^16.1.0"))

        implementation(npm("@angular/core", "^16.1.0"))
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
    create("ngServe", Exec::class) {
      dependsOn("build")
      // TODO: copy typescript source files to build dir
      mutableListOf<String>(
        "npx"
      ).also {
        if (isWindows) {
          it.add(0, "cmd")
          it.add(1, "/c")
        }
      }.also {
        it += "-w"
        it += "${rootProject.name}-${project.name}"
//        it +=
        workingDir =
          rootProject.layout.buildDirectory
          .dir("js")
//          .dir("js/packages/${rootProject.name}-${project.name}")
          .get()
          .asFile
//          .path
      }.also {
        it += "run"
        it += "ng"
        it += "serve"
      }.also {
        println(it)
        commandLine(it)
      }
    }
  }
}
