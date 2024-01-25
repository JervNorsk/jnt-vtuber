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
    browser()
    binaries.executable()
  }
  sourceSets {
    val jsMain by getting {
      kotlin {
        srcDir("src/jsMain/typescript")
      }
    }
  }
}
