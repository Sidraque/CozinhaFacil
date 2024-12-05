@file:Suppress("DEPRECATION")

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.cozinhafacil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cozinhafacil"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    packaging {
        jniLibs {
            // useLegacyPackaging = true // Removido para evitar possíveis conflitos
        }
        resources {
            excludes += "/META-INF/DEPENDENCIES"
            pickFirst("lib/x86/libc++_shared.so")
            pickFirst("lib/x86_64/libc++_shared.so")
            pickFirst("lib/armeabi-v7a/libc++_shared.so")
            pickFirst("lib/arm64-v8a/libc++_shared.so")
        }
    }

    buildTypes {
        getByName("debug") {
            ndk {
                // debugSymbolLevel = "none" // Removido para testar a compilação com debug symbols habilitados
            }
        }
    }

    ndkVersion = "25.1.8937393" // Adicionado para garantir a versão do NDK
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0") // Atualizado para a versão mais recente
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.2.0") // Atualizado para manter a consistência
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.navigation:navigation-compose:2.6.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.0")

    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
}
