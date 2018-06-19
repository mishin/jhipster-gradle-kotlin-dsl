package tech.jhipster

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import java.lang.Runtime.getRuntime

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
    maxParallelForks                = getRuntime().availableProcessors()
    setForkEvery(100)
    reports.html.isEnabled          = true
    reports.junitXml.isEnabled      = true
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    systemProperty("log4j2.debug", "")
    testLogging {
        exceptionFormat = FULL
        events("passed", "skipped", "failed")
    }
}
