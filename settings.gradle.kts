pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.android.settings") version "8.9.1"
}

android {
    minSdk = 28
    compileSdk = 35
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "realestate"
include(":app")
include(":core:model")
include(":core:network")
include(":core:testing")
include(":core:database")
include(":core:data")
include(":core:common")
include(":core:domain")
