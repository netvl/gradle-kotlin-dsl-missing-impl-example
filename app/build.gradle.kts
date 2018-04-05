plugins {
    id("com.android.application").version("3.0.1")
    kotlin("android").version("1.2.31")
    kotlin("android.extensions").version("1.2.31")
    kotlin("kapt").version("1.2.31")
}

val sdkVersion = "27"

android {
    compileSdkVersion(27)
    buildToolsVersion("27.0.1")

    defaultConfig {
        applicationId = "io.github.netvl.example"
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        minSdkVersion("17")
        targetSdkVersion(sdkVersion)
    }

    packagingOptions {
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/io.netty.versions.properties")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        disable("InvalidPackage", "HardcodedText")
    }

    dexOptions {
        preDexLibraries = false
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.2.31")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.2.31")

    compile("com.android.support:appcompat-v7:$sdkVersion.0.1")
    compile("com.android.support:design:$sdkVersion.0.1")
    compile("com.android.support:support-v4:$sdkVersion.0.1")
    compile("com.android.support:recyclerview-v7:$sdkVersion.0.1")
    compile("com.android.support.constraint:constraint-layout:1.1.0-beta5")
    compile("com.android.support:multidex:1.0.3")

    compile("android.arch.lifecycle:extensions:1.1.0")
    compile("android.arch.persistence.room:runtime:1.0.0")
    kapt("android.arch.persistence.room:compiler:1.0.0")

    compile("org.jetbrains.anko:anko:0.10.4")

    compile("com.daimajia.swipelayout:library:1.2.0@aar")

    compile("com.github.joshjdevl.libsodiumjni:libsodium-jni-aar:1.0.8")

    compile("com.github.salomonbrys.kodein:kodein:4.1.0")
    compile("com.github.salomonbrys.kodein:kodein-android:4.1.0")

    compile("com.google.code.findbugs:jsr305:3.0.0")
    compile("javax.annotation:javax.annotation-api:1.2")

    compile("org.slf4j:slf4j-api:1.7.25")
    compile("org.slf4j:slf4j-android:1.7.25")
//    compile("io.github.microutils:kotlin-logging:1.4.7")

    compile("com.jakewharton.threetenabp:threetenabp:1.0.5")

    compile(project(":server")) {
        isTransitive = true
        exclude(module = "libsodium-jni")
    }

    testCompile("junit:junit:4.12")
}

