plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.realestate.testing"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.test)
    implementation(libs.junit)
}