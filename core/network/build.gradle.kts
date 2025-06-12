plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.realestate.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":core:model"))
    testImplementation(project(":core:testing"))

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.androidx.arch.core)

    // coroutines
    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // hilt
    implementation(libs.android.hilt)
    ksp(libs.hilt.compiler)
}