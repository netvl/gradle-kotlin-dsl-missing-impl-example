import com.google.protobuf.gradle.ExecutableLocator
import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.ProtobufConfigurator
import com.google.protobuf.gradle.ToolsLocator

plugins {
    java
    kotlin("jvm").version("1.2.31")
    id("com.google.protobuf").version("0.8.5")
}

protobuf {
    protobuf.run {
        protoc(closureOf<ExecutableLocator> {
            artifact = "com.google.protobuf:protoc:3.5.0"
        })

        plugins(closureOf<NamedDomainObjectContainer<ExecutableLocator>> {
            create("javalite") {
                artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0"
            }
            create("grpc") {
                artifact = "io.grpc:protoc-gen-grpc-java:1.8.0"
            }
        })

        generateProtoTasks(closureOf<ProtobufConfigurator.GenerateProtoTaskCollection> {
            all().forEach {
                it.builtins {
                    remove("java"())
                }
                it.plugins {
                    "javalite" { }
                    "grpc" {
                        option("lite")
                    }
                }
            }
        })
    }
}

java {
    sourceSets {
        "main" {
            java {
                srcDirs("src/main/java", "build/generated/source/proto/main/grpc", "build/generated/source/proto/main/javalite")
            }
        }
    }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.2.31")
    compile("org.jetbrains.kotlin:kotlin-reflect:1.2.31")
    compile("com.github.joshjdevl.libsodiumjni:libsodium-jni:1.0.8")
    compile("io.grpc:grpc-netty:1.8.0")
    compile("io.grpc:grpc-protobuf-lite:1.8.0")
    compile("io.grpc:grpc-stub:1.8.0")
    compile("org.slf4j:slf4j-api:1.7.25")
    compile("io.github.microutils:kotlin-logging:1.4.7")
    compile("com.madgag.spongycastle:core:1.58.0.0")
    testCompile("org.slf4j:slf4j-simple:1.7.25")
}
