import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

the<KotlinJvmProjectExtension>().experimental.coroutines = Coroutines.ENABLE

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget         = VERSION_1_8.toString()
        suppressWarnings  = false
        verbose           = true
        freeCompilerArgs += arrayOf("-Xjsr305=strict", "-java-parameters", "-Xenable-jvm-default")
    }
}
