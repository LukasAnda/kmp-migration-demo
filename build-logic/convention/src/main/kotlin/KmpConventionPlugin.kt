import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("com.android.library")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            androidTarget {
                compilations.all {
                    compilerOptions.configure {
                        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
                    }
                }
            }

            iosX64()
            iosArm64()
            iosSimulatorArm64()

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

        extensions.configure<LibraryExtension> {
            namespace = deriveNamespace()
            compileSdk = 35
            defaultConfig {
                minSdk = 26
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }

    private fun Project.deriveNamespace(): String {
        val modulePath = path
            .removePrefix(":")
            .replace(":", ".")
            .replace("-", ".")
        return "com.example.weather.$modulePath"
    }
}
