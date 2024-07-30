plugins {
    id("com.android.library")
    `maven-publish`
}

val jarCircleImageView by tasks.registering(Jar::class) {
    archiveClassifier.set("circleImageView")
    from("src/main/java/com/example/circleimagelib/CircleImageView.java")
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.example"
            artifactId = "image"
            version = "1.00"
//            from(components["release"])
            artifact(jarCircleImageView.get())

        }
    }
    repositories {
        maven {
            url = uri("file://${rootDir}/repo")
        }
    }
}


android {
    namespace = "com.example.circleimagelib"
    compileSdk = 34

//    publishing {
//        singleVariant("release") {
//        }
//    }


    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}