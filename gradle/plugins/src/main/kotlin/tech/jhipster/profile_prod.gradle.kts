package tech.jhipster

import com.gorylenko.GenerateGitPropertiesTask
import com.gorylenko.GitPropertiesPluginExtension
import com.moowork.gradle.node.yarn.YarnTask
import org.springframework.boot.gradle.tasks.run.BootRun
import kotlin.collections.mutableListOf

plugins {
    id("org.springframework.boot")
    id("com.gorylenko.gradle-git-properties")
    id("com.moowork.node")
}

var profiles = "prod"
if (project.hasProperty("no-liquibase")) {
    profiles += ",no-liquibase"
}

if (project.hasProperty("swagger")) {
    profiles += ",swagger"
}

tasks.withType<BootRun>().configureEach {
    args = mutableListOf<String>()
}

val webpackTest = tasks.register("webpack_test", YarnTask::class.java) {
    dependsOn("yarn_install")
    args += arrayOf("run", "webpack:test")
}

val webpack = tasks.register("webpack", YarnTask::class.java) {
    dependsOn("yarn_install")
    args += arrayOf("run", "webpack:prod")
}

configure<WarPluginConvention> {
    webAppDirName = "build/www/"
}

tasks.withType<ProcessResources>().configureEach {
    filesMatching("**/application.yml") {
        filter {
            it.replace("#project.version#", version.toString())
        }
    }
    filesMatching("**/bootstrap.yml") {
        filter {
            it.replace("#spring.profiles.active#", profiles)
        }
    }
    dependsOn("webpack")
}

tasks.withType<GenerateGitPropertiesTask>().configureEach {
    onlyIf { ! source.isEmpty }
}

configure<GitPropertiesPluginExtension> {
    keys += arrayOf("git.branch", "git.commit.id.abbrev", "git.commit.id.describe")
}

tasks.withType<Test>().configureEach {
    dependsOn("webpack_test")
}
