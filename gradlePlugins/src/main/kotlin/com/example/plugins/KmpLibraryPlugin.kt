package com.example.plugins

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.multiplatform")
        }

        extensions.configure<LibraryExtension> {
            val modulePath = project.path
                .removePrefix(":shared:")
                .removePrefix(":platform:")
                .replace(":", ".")
                .replace("-", ".")
            namespace = "com.example.weather.$modulePath"
            compileSdk = 35
            defaultConfig {
                minSdk = 26
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }

        extensions.configure<KotlinMultiplatformExtension> {
            androidTarget {
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
                }
            }

            // iOS framework in every module - this is the SLOW pattern we're fixing in step-4
            // Use "shared" as baseName for :shared:app to match project.yml expectations
            val frameworkBaseName = if (project.path == ":shared:app") "shared" else project.name
            listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = frameworkBaseName
                    isStatic = true
                }
            }

            sourceSets.all {
                languageSettings.optIn("kotlin.RequiresOptIn")
            }
        }
    }
}
