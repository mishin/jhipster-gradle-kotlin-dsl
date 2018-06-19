package tech.jhipster

import org.gradle.internal.os.OperatingSystem
import java.lang.Runtime.*
import java.time.ZoneId
import java.time.ZoneId.*
import java.time.ZonedDateTime
import java.time.ZonedDateTime.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.*

configurations.maybeCreate("liquibase")

dependencies {
    "liquibase"("org.liquibase.ext:liquibase-hibernate5:${project.extra["liquibase_hib5_version"]}")
}

var pathingLiquibaseJar: TaskProvider<Jar>? = null

val initPaths = tasks.register("initPaths") {
    group = "liquibase"
    if (OperatingSystem.current().isWindows) {
        pathingLiquibaseJar = tasks.register("pathingLiquibaseJar", Jar::class.java) {
            group = "liquibase"
            dependsOn(configurations["liquibase"])
            appendix = "pathingLiquibase"
            doFirst {
                manifest.attributes["Class-Path"] = project
                .convention.getPlugin(JavaPluginConvention::class)
                .sourceSets["main"].runtimeClasspath.plus(configurations["liquibase"])
                .joinToString(separator = " ") { it.toURI().toURL().toString().replaceFirst("file:/", "/") }
            }
        }
        dependsOn("pathingLiquibaseJar")
    }
}

fun liquibaseCommand(command: String) {

    javaexec {
        if (OperatingSystem.current().isWindows) {
            classpath = files(pathingLiquibaseJar?.get()?.archivePath)
        } else {
            classpath = convention.getPlugin(JavaPluginConvention::class).sourceSets["main"].runtimeClasspath
            classpath += configurations["liquibase"]
        }
        main = "liquibase.integration.commandline.Main"

        args!! += "--changeLogFile=src/main/resources/config/liquibase/changelog/${now(of("Z")).format(ofPattern("yyyyMMddHHmmss"))}_changelog.xml"
        args!! += "--referenceUrl=hibernate:spring:tech.jhipster.domain?dialect=org.hibernate.dialect.H2Dialect&hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy"
        args!! +=  "--username=gradlekotlindsl"
        args!! +=  "--password="
        args!! +=  "--url=jdbc:h2:file:./build/h2db/db/gradlekotlindsl"
        args!! +=  "--driver=org.h2.Driver"
        args!! +=  command
    }
}

tasks.register("liquibaseDiffChangeLog") {
    dependsOn("initPaths")
    doLast {
        liquibaseCommand("diffChangeLog")
    }
}

tasks.register("liquibaseClearChecksums") {
    dependsOn("initPaths")
    doLast {
        liquibaseCommand("clearChecksums")
    }
}

