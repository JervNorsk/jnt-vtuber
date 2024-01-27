import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
  // IDE
  // ------------------------------------------------------------------------
  id("idea")

  // Kotlin
  // ------------------------------------------------------------------------
  kotlin("multiplatform") apply false
//    kotlin("kapt")
}

idea {
  project {
    languageLevel = IdeaLanguageLevel(JavaVersion.VERSION_21)
  }
}

allprojects {
  group = "io.github.jervnorsk.tools.vtuber";
  version = "0.1.0"
}

subprojects {
  group = "$group.${path.replace(":", ".").substring(1)}"
}
