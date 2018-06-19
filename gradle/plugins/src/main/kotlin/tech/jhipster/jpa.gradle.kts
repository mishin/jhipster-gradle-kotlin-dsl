package tech.jhipster

import org.gradle.plugins.ide.eclipse.model.EclipseModel
import org.gradle.plugins.ide.eclipse.model.Classpath
import org.gradle.plugins.ide.eclipse.model.SourceFolder
import org.gradle.plugins.ide.idea.model.IdeaModel

plugins {
    eclipse
    idea
    java
}

// TODO("https://github.com/gradle/gradle/issues/5448#issuecomment-390190509")
the<IdeaModel>().module.sourceDirs = setOf(file("${project.buildDir}/classes/java/main"))

val delegate: Project = project
configure<EclipseModel> {
    classpath.file.whenMerged.add(object : Action<Classpath> {
        override fun execute(cp: Classpath) = cp.entries.add(SourceFolder("${delegate.buildDir}/classes/java/main", null)) as Unit
    })
    classpath {
        containers.remove("org.eclipse.jdt.launching.JRE_CONTAINER")
        containers("org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8")
    }
}

dependencies {
    "annotationProcessor"("javax.xml.bind:jaxb-api:${project.extra["javax-jaxb.version"]}") {
        because("MetaModel and some other components require this to be on annotationProcessor classpath")
    }
    "annotationProcessor"("javax.annotation:javax.annotation-api:${project.extra["javax-annotation.version"]}") {
        because("Annotation Processors require this to be on annotationProcessor classpath")
    }
    "annotationProcessor"("org.hibernate:hibernate-jpamodelgen:${project.extra["hibernate.version"]}")

    "annotationProcessor"("org.hibernate.validator:hibernate-validator-annotation-processor:${project.extra["hibernate-validator.version"]}")

    "implementation"("javax.xml.bind:jaxb-api:${project.extra["javax-jaxb.version"]}") {
        because("Java 9+")
    }
    "implementation"("javax.annotation:javax.annotation-api:${project.extra["javax-annotation.version"]}") {
        because("Java 9+")
    }

    "implementation"("org.hibernate.validator:hibernate-validator:${project.extra["hibernate-validator.version"]}")

//    "implementation"(group="org.infinispan", name="infinispan-hibernate-cache")
    "implementation"("org.hibernate:hibernate-core:${project.extra["hibernate.version"]}") {
        exclude(group = "org.javassist")
        exclude(group = "dom4j")
        exclude(group = "antlr")
    }
}
