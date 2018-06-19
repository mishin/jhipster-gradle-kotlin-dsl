package tech.jhipster

import org.gradle.internal.os.OperatingSystem
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot")
}

dependencies {
    "annotationProcessor"(SpringBootPlugin.BOM_COORDINATES)
    "implementation"(SpringBootPlugin.BOM_COORDINATES)
}

// We attempt here to substitute this with `id("com.github.ManifestClasspath") version "0.1.0-RELEASE"`
//if (OperatingSystem.current().isWindows) {
//    // https://stackoverflow.com/questions/40037487/the-filename-or-extension-is-too-long-error-using-gradle
//    //TODO("See if `git config --system core.longpaths true` resolves this")
//    val classpathJar = tasks.register("classpathJar", Jar::class.java) {
//        dependsOn(project.configurations["runtime"])
//        appendix = "classpath"
//
//        doFirst {
//            manifest.attributes["Class-Path"] = project.configurations.findByName("liquibase")
//                ?.joinToString(separator = " ") { it.toURI().toURL().toString().replaceFirst("file:/", "/") }
//        }
//    }
//    tasks.withType<BootRun>().configureEach {
//        dependsOn(classpathJar.get())
//        doFirst {
//            classpath = files("$buildDir/classes/java/main", "$buildDir/resources/main", classpathJar.get().archivePath)
//        }
//    }
//}
