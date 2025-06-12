plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.realestate.data"
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))
    api(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    testImplementation(project(":core:testing"))

    // Coroutines
    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)

    // Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.logging.interceptor)

    // Hilt
    implementation(libs.android.hilt)
    ksp(libs.hilt.compiler)

    // Paging
    implementation(libs.androidx.paging.common.android)
    implementation(libs.paging)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)
}