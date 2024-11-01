// Arquivo de configuração no nível do projeto (raiz)
plugins {
    id("com.android.application") version "8.5.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Atualize para 1.9.0
    id("com.google.gms.google-services") version "4.4.2" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

