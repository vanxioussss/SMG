plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.realestate.listproperties"
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:testing"))

    // hilt
    implementation(libs.android.hilt)
    debugImplementation(libs.ui.tooling)
    ksp(libs.hilt.compiler)

    // coroutines
    implementation(libs.coroutines.android)

    // Compose
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Glide Compose
    implementation(libs.glide.compose)

    // Serialization
    implementation(libs.kotlinx.serialization)

    // Paging
    implementation(libs.androidx.paging.common.android)
    implementation(libs.paging)
    testImplementation(libs.androidx.paging.testing)
    implementation(libs.paging.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)
    implementation(libs.core.ktx)
    testImplementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}