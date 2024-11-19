// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val compose_version by extra("1.3.1") // Compose 稳定版
    val kotlin_version by extra("1.7.10") // 对应的 Kotlin 版本

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.0") // 更新到支持SDK 35的版本
        // 注意：Compose 版本有时候需要要求 Kotlin 到达一定的版本，请同步更新
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}