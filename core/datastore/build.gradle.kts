plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.realestate.datastore"
}

dependencies {
    implementation(libs.androidx.dataStore)
    testImplementation(project(":core:testing"))

    // Hilt
    implementation(libs.android.hilt)
    ksp(libs.hilt.compiler)

    // Test Coroutines
    testImplementation(libs.coroutines.test)

    // Testing
    testImplementation(libs.junit)
    implementation(libs.core.ktx)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}