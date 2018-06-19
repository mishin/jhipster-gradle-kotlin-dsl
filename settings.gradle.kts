import org.gradle.api.PathValidation.*
import java.io.FileFilter

rootProject.name = "gradlekotlindsl"
enableFeaturePreview("IMPROVED_POM_SUPPORT")
enableFeaturePreview("STABLE_PUBLISHING")

includeBuild(file(path = "file://$rootDir/gradle/plugins", validation = DIRECTORY))

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id.startsWith(prefix = "tech.jhipster", ignoreCase = true)) {
                useModule("tech.jhipster:gradle-plugins:${requested.version}") // version ignored anyway for composite build
            }
        }
    }
}


