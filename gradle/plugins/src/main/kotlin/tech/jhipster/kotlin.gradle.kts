package tech.jhipster

import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(module = "jvm")
    kotlin(module = "plugin.spring")
}

the<KotlinJvmProjectExtension>().experimental.coroutines = Coroutines.ENABLE

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget         = VERSION_1_8.toString()
        suppressWarnings  = false
        verbose           = true
        freeCompilerArgs += project.extra["kotlin_free_compiler_args"].toString()
    }
}

dependencies {
    //BOM
    "implementation"("com.fasterxml.jackson:jackson-bom:${project.extra["jackson.version"]}")
    //BOM
    //TODO("See if we can remove explicit versions")
    "implementation"(kotlin(module="stdlib-jdk8"))
    "implementation"(kotlin(module="reflect"))
    "implementation"(group="com.fasterxml.jackson.module", name="jackson-module-kotlin")
}
