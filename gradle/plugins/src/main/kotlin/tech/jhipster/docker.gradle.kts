package tech.jhipster

import org.gradle.api.file.DuplicatesStrategy.*
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

plugins {
    id("com.bmuschko.docker-remote-api")
}

val copyDockerFiles = tasks.register("copyDockerFiles", Copy::class.java) {
    description = "Copy Dockerfile and required data to build directory"
    group       = "Docker"
    setDuplicatesStrategy(EXCLUDE)
    from(file("src/main/docker"))
    from(file("${project.buildDir}/libs"))
    include("*")
    exclude("**/*.yml")
    into(file("${project.buildDir}/docker"))
}

//TODO("https://github.com/bmuschko/gradle-docker-plugin/blob/master/README.asciidoc#reactive-streams")
val buildDocker = tasks.register("buildDocker", DockerBuildImage::class.java) {
    description = "Package application as Docker image"
    group       = "Docker"
    dependsOn  += copyDockerFiles.get()
    inputDir    = project.file("${project.buildDir}/docker")
    val tag     = project.name.splitToSequence(' ', '-', '.', '_').joinToString(separator = "") { it.substring(0, 1).toUpperCase() }
    tags        = setOf("$tag:latest", "$tag:${project.version}")
}
