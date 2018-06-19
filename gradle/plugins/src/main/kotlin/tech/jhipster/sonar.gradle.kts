package tech.jhipster

plugins {
    id("org.sonarqube")
    id("jacoco")
}

tasks.withType<JacocoReport>().configureEach {
    reports {
        xml.isEnabled = true
    }
}
