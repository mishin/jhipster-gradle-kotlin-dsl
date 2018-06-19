@file:Suppress("UNUSED")

/**
 * @author <a href="mailto:43personal@gmail.com">soberich</a> on 15-Jun-18
 */
object Plugins {

    object Env {
        const val NODE_VERSION = "8.11.2"
        const val NPM_VERSION  = "5.8.0"
        const val YARN_VERSION = "1.6.0"
    }

    object Versions {
        const val KOTLIN_STABLE = "1.2.50"
        const val DOCKER        = "3.3.3"
        //# The Spring version should match the one managed by https://mvnrepository.com/artifact/io.github.jhipster/jhipster-dependencies/${jhipster-dependencies-version}
        const val SPRING_BOOT   = "2.0.2.RELEASE"
        const val SPRING_DMGMT  = "1.0.5.RELEASE"
        const val NODE          = "1.2.0"
        const val GIT_PROPS     = "1.5.1"
        const val SONAR         = "2.6.2"

    }

    const val DOCKER       = "com.bmuschko:gradle-docker-plugin:${Versions.DOCKER}"
    const val SPRING_BOOT  = "org.springframework.boot:spring-boot-gradle-plugin:${Versions.SPRING_BOOT}"
    const val SPRING_DMGMT = "io.spring.gradle:dependency-management-plugin:${Versions.SPRING_DMGMT}"
    const val NODE         = "com.moowork.gradle:gradle-node-plugin:${Versions.NODE}"
    const val GIT_PROPS    = "gradle.plugin.com.gorylenko.gradle-git-properties:gradle-git-properties:${Versions.GIT_PROPS}"
    const val SONAR        = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.SONAR}"
}


object Deps {

    object Versions {
        const val JACKSON          = "2.9.5"
        const val RESTEASY         = "4.0.0.Beta3"
        const val JAXB             = "2.3.0"
        const val ANNOTATION       = "1.3.2"
        //# The hibernate version should match the one managed by https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/${spring-boot.version}
        const val HIBERNATE        = "5.2.17.Final"
        const val VALIDATOR        = "6.0.9.Final"
        const val LIQUIBASE_HIB5   = "3.6"
        const val JJWT             = "0.9.0"
    }

    object BOMs {
        const val JACKSON  = "com.fasterxml.jackson:jackson-bom:${Versions.JACKSON}"
        const val RESTEASY = "org.jboss.resteasy:resteasy-bom:${Versions.RESTEASY}"
    }

    object Javax {
        const val JAXB       = "javax.xml.bind:jaxb-api:${Versions.JAXB}"
        const val ANNOTATION = "javax.annotation:javax.annotation-api:${Versions.ANNOTATION}"
    }

    object Libs {
        const val HIBERNATE      = "org.hibernate:hibernate-core:${Versions.HIBERNATE}"
        const val JPAMODELGEN    = "org.hibernate:hibernate-jpamodelgen:${Versions.HIBERNATE}"
        const val VALIDATOR_AP   = "org.hibernate.validator:hibernate-validator-annotation-processor:${Versions.VALIDATOR}"
        const val LIQUIBASE_HIB5 = "org.liquibase.ext:liquibase-hibernate5:${Versions.LIQUIBASE_HIB5}"
        const val JJWT           = "io.jsonwebtoken:jjwt:${Versions.JJWT}"
    }
}



