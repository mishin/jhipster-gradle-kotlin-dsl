package tech.jhipster

dependencies {
    //BOM
    "implementation"("com.fasterxml.jackson:jackson-bom:${project.extra["jackson.version"]}")
    //BOM

    "annotationProcessor"("javax.xml.bind:jaxb-api:${project.extra["javax-jaxb.version"]}") {
        because("Annotation Processors require this to be on annotationProcessor classpath")
    }
    "annotationProcessor"("javax.annotation:javax.annotation-api:${project.extra["javax-annotation.version"]}") {
        because("Annotation Processors require this to be on annotationProcessor classpath")
    }

    /*-------------------------------------------
    |       J A C K S O N  3.0 MIGRATION        |
    ============================================*/
    /**RESTEASY*/ // change to implementation on migration
//    "implementation"("org.jboss.resteasy:resteasy-jackson2-provider") {
//        isTransitive = false
//    }
//    "implementation"(group="org.jboss.resteasy",             name="resteasy-jaxb-provider") {
//        isTransitive = false
//    }
    // except this one - stays `compileOnly`
    "compileOnly"("org.jboss.resteasy:resteasy-client")
    /**RESTEASY*/ // change to implementation on migration
    /**JACKSON*/ // change to implementation on migration
    "compileOnly"(group = "com.fasterxml.jackson.core", name = "jackson-databind")
//    "implementation"(group="com.fasterxml.jackson.core",     name="jackson-core")
    "compileOnly"(group = "com.fasterxml.jackson.core", name = "jackson-annotations")
//    "implementation"(group="com.fasterxml.jackson.module",   name="jackson-module-jaxb-annotations")
//    "implementation"(group="com.fasterxml.jackson.jaxrs",    name="jackson-jaxrs-json-provider")
//    "implementation"(group="com.fasterxml.jackson.jaxrs",    name="jackson-jaxrs-base")
//    "implementation"("com.github.fge:json-patch:1.9")
//    "implementation"("com.github.fge:jackson-coreutils:1.8")
    "compileOnly"(group = "com.fasterxml.jackson.datatype", name = "jackson-datatype-jsr310")
    /**JACKSON*/
    /*-------------------------------------------
    |       J A C K S O N  3.0 MIGRATION        |
    ============================================*/


    "implementation"("org.zalando:jackson-datatype-money:1.0.2")
    "implementation"("org.javamoney:moneta:1.2.1")
    "implementation"(group = "com.fasterxml.jackson.module", name = "jackson-module-parameter-names")

    "implementation"("javax.xml.bind:jaxb-api:${project.extra["javax-jaxb.version"]}") {
        because("Java 9+")
    }
    "implementation"("javax.annotation:javax.annotation-api:${project.extra["javax-annotation.version"]}") {
        because("Java 9+")
    }
}
