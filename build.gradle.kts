import org.gradle.api.JavaVersion.*
import org.springframework.boot.gradle.tasks.bundling.BootWar
import org.springframework.boot.gradle.dsl.SpringBootExtension
import org.springframework.boot.gradle.tasks.run.BootRun
import org.springframework.boot.gradle.tasks.buildinfo.BuildInfo
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import com.moowork.gradle.node.NodeExtension

// -ea flag needed
assert(current().isJava8Compatible) { "At least Java 8 is required, current JVM is ${current()}" }

plugins {
	war
    id("tech.jhipster.jpa")
    id("tech.jhipster.spring-boot")
    id("tech.jhipster.sonar")
	id("com.github.ManifestClasspath") version "0.1.0-RELEASE"
    id("com.moowork.node")             version "1.2.0"
}

group       = "tech.jhipster"
version     = "0.0.1-SNAPSHOT"
description = "${name.replace(oldChar = '-', newChar = ' ').toUpperCase()} of JHipster Gradle Kotlin DSL proposal"

repositories {
    jcenter()
    maven(url = "https://dl.bintray.com/jhipster/maven/")
    maven(url = "http://repo.spring.io/plugins-release/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    maven(url = "https://dl.bintray.com/iherasymenko/lombok/")
}

defaultTasks("bootRun")

tasks.withType<BootWar>().configureEach {
    mainClassName = "tech.jhipster.GradlekotlindslApp"
}

configure<SpringBootExtension> {
    mainClassName = "tech.jhipster.GradlekotlindslApp"
    buildInfo()
}

if (project.hasProperty("prod"))       apply(plugin = "tech.jhipster.profile_prod")
else                                   apply(plugin = "tech.jhipster.profile_dev")

if (project.hasProperty("graphite"))   apply(plugin = "tech.jhipster.graphite")
if (project.hasProperty("prometheus")) apply(plugin = "tech.jhipster.prometheus")

val cleanResources = tasks.register("cleanResources", Jar::class.java) {
    delete("build/resources")
}

configurations.all {
    exclude(module = "spring-boot-starter-tomcat")
    exclude(group = "com.vaadin.external.google", module = "android-json")
}

dependencies {
    //BOM
    implementation("io.github.jhipster:jhipster-dependencies:${extra["jhipster-dependencies-version"]}")
    //BOM

    annotationProcessor("org.mapstruct:mapstruct-processor:${extra["mapstruct_version"]}")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }

	//TODO("https://github.com/rzwitserloot/lombok/issues/1603 solved in `1.18.1` e.g. `EDGE` release => improve data classes to remove boilerplate")
//    annotationProcessor("org.projectlombok:lombok:1.18.1")
//    compileOnly("org.projectlombok:lombok:1.18.1")

	//TODO("Use only JAX-RS config e.g. annotations, so controller layer is completely non-dependent to spring")
//    compileOnly("javax.ws.rs:javax.ws.rs-api:2.1")
    //FIXME: this causes tests to fail now
//    runtimeOnly("org.jboss.resteasy:resteasy-spring-boot-starter:2.0.0.Beta1")

	//TODO("Further improvement is to externalize spring configuration completely to not just a separate module but also to 1 single file with declarative `fu` config instead severat `@Configuration` classes")
//    implementation("org.springframework.fu:spring-fu:1.0.0-SNAPSHOT")

    implementation("io.github.jhipster:jhipster-framework") {
        isChanging = true
    }

    // TODO To remove after final Spring Boot 2 release
    implementation("javax.xml.bind:jaxb-api")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("io.dropwizard.metrics:metrics-core")
    implementation("io.dropwizard.metrics:metrics-jcache")
    implementation("io.dropwizard.metrics:metrics-json")
    implementation("io.dropwizard.metrics:metrics-jvm")
    implementation("io.dropwizard.metrics:metrics-servlet")
    implementation("io.dropwizard.metrics:metrics-servlets")
    implementation("net.logstash.logback:logstash-logback-encoder")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hppc")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")
    implementation("com.ryantenney.metrics:metrics-spring")
    implementation("javax.cache:cache-api")
    implementation("org.hibernate:hibernate-core")
    implementation("com.zaxxer:HikariCP")
    implementation("org.apache.commons:commons-lang3")
    implementation("commons-io:commons-io")
    implementation("javax.transaction:javax.transaction-api")
    implementation("org.ehcache:ehcache")
    implementation("org.hibernate:hibernate-jcache")
    implementation("org.hibernate:hibernate-entitymanager")
    implementation("org.hibernate:hibernate-envers")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.liquibase:liquibase-core")
    implementation("com.mattbertolini:liquibase-slf4j")
    implementation("org.springframework.boot:spring-boot-loader-tools")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-actuator") {
        exclude(module = "micrometer-core")
    }
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    // needed to get around elasticsearch stacktrace about jna not found
    // https://github.com/elastic/elasticsearch/issues/13245
    implementation("net.java.dev.jna:jna")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.zalando:problem-spring-web")
    //implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    //implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-spring-service-connector")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-data")
    implementation("org.springframework.security:spring-security-web")
    implementation("io.jsonwebtoken:jjwt")
    implementation("io.springfox:springfox-swagger2") {
        exclude(module = "mapstruct")
    }
    implementation("io.springfox:springfox-bean-validators")
    implementation("mysql:mysql-connector-java")
    implementation("org.mapstruct:mapstruct-jdk8:${extra["mapstruct_version"]}")

    runtimeOnly("org.yaml:snakeyaml"){
        isForce = true
    }

    testImplementation("com.jayway.jsonpath:json-path")
    testImplementation ("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.assertj:assertj-core")
    testImplementation("junit:junit")
    testImplementation("org.mockito:mockito-core")
    testImplementation("com.mattbertolini:liquibase-slf4j")
    testImplementation("org.hamcrest:hamcrest-library")
}
