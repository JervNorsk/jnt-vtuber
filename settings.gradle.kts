dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

pluginManagement {
    resolutionStrategy {
        plugins {
            // Kotlin
            // ----------------------------------------------------------------
            kotlin("multiplatform") version (extra["kotlin.version"] as String)

            // Kotlin Plugins
            // ----------------------------------------------------------------
            kotlin("plugin.serialization") version (extra["kotlin.version"] as String)
        }
    }
}

fun includeModule(moduleDir: File, moduleName: String = moduleDir.name) {
  fileTree(moduleDir)
    .filter { it.name.matches("^build.gradle([.]kts?)".toRegex()) }
    .map { it.parentFile }
    .forEach { file ->
      include(":$moduleName:${file.name}")
      project(":$moduleName:${file.name}").apply {
        projectDir = file
      }
    }
}

file("modules")
  .listFiles()
  ?.forEach { file ->
    includeModule(file)
  }
