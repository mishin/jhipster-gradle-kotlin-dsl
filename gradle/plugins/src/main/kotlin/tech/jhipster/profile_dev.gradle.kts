package tech.jhipster

import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.yarn.YarnTask
import org.springframework.boot.gradle.tasks.run.BootRun
import kotlin.collections.mutableListOf

plugins {
    id("org.springframework.boot")
    id("com.moowork.node")
}

dependencies {
    "implementation"(group = "org.springframework.boot", name = "spring-boot-devtools") //comes ONLY from Spring
    "implementation"(group = "com.h2database", name = "h2") //comes ONLY from Spring TODO("really?")
}

var profiles = "dev"
if (project.hasProperty("no-liquibase")) {
    profiles += ",no-liquibase"
}

tasks.withType<BootRun>().configureEach {
    args = mutableListOf<String>()
}

tasks.register("webpackBuildDev", YarnTask::class.java) {
    args += arrayOf("run", "webpack:build")
}

configure<WarPluginConvention> {
    webAppDirName = "src/main/webapp/"
}

tasks.withType<ProcessResources>().configureEach {
    filesMatching("**/application.yml") {
        filter {
            it.replace("#project.version#", version.toString())
        }
        filter {
            it.replace("#spring.profiles.active#", profiles)
        }
    }
}

if (project.hasProperty("nodeInstall")) {
    configure<NodeExtension> {
        val node_version by project.extra { this.version }
        version = "$node_version"
        val npm_version by project.extra { this.npmVersion }
        npmVersion = "$npm_version"
        val yarn_version by project.extra { this.yarnVersion }
        yarnVersion = "$yarn_version"
        download = true
    }
}
