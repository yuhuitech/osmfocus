val kotlinVersion by project.extra("1.4.21")

buildscript {
    extra.apply {
        set("kotlin", "1.4.21")
    }

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx/")
        maven("http://storage.googleapis.com/r8-releases/raw")
    }

    dependencies {
        classpath("com.android.tools:r8:2.2.60")
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath(kotlin("gradle-plugin", version = project.extra["kotlin"] as String?))
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.14")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}

val secretsPropertiesFile = rootProject.file("secrets.properties")
val secretProperties by project.extra(java.util.Properties())
if (secretsPropertiesFile.exists()) {
    secretProperties.load(java.io.FileInputStream(secretsPropertiesFile))
} else {
    secretProperties.setProperty("signing_keystore_file", System.getenv("SIGNING_KEYSTORE_FILE"))
    secretProperties.setProperty("signing_keystore_password", System.getenv("SIGNING_KEYSTORE_PASSWORD"))
    secretProperties.setProperty("signing_key_alias", System.getenv("SIGNING_KEY_ALIAS"))
    secretProperties.setProperty("signing_key_password", System.getenv("SIGNING_KEY_PASSWORD"))
}
