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

            // Only declare iOS targets - submodules compile to .klib only
            // Framework production moved to shared:app (the umbrella module)
            iosX64()
            iosArm64()
            iosSimulatorArm64()

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
