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

file("services")
  .listFiles()
  ?.forEach { file ->
    include(":${file.name}")
    project(":${file.name}").apply {
      projectDir = file
    }
  }
