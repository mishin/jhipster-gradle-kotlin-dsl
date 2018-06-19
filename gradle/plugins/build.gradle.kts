import org.gradle.kotlin.dsl.plugins.precompiled.PrecompiledScriptPlugins
import org.gradle.api.JavaVersion.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

apply<PrecompiledScriptPlugins>()

repositories {
    gradlePluginPortal()
    jcenter()
}

group   = "tech.jhipster"
version = "1.0.0"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget         = VERSION_1_8.toString()
        suppressWarnings  = false
        verbose           = true
        freeCompilerArgs += arrayOf("-Xjsr305=strict", "-java-parameters", "-Xenable-jvm-default")
    }
}

/*plugin*/ dependencies {
    implementation(kotlin(module = "gradle-plugin", version = Plugins.Versions.KOTLIN_STABLE))
    implementation(kotlin(module = "stdlib-jdk8",   version = Plugins.Versions.KOTLIN_STABLE))
    implementation(kotlin(module = "reflect",       version = Plugins.Versions.KOTLIN_STABLE))
    implementation(kotlin(module = "allopen",       version = Plugins.Versions.KOTLIN_STABLE))
    implementation(Plugins.DOCKER)
    implementation(Plugins.SPRING_BOOT)
    implementation(Plugins.SPRING_DMGMT)
    implementation(Plugins.NODE)
    implementation(Plugins.GIT_PROPS)
    implementation(Plugins.SONAR)
}
