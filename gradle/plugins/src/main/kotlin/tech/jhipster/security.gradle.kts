package tech.jhipster

dependencies {
    "implementation"("io.jsonwebtoken:jjwt:${project.extra["jjwt_version"]}")
//    "implementation"("org.apache.shiro:shiro-core:${project.extra["shiroVersion"]}")
//    "implementation"("org.apache.shiro:shiro-jaxrs:${project.extra["shiroVersion"]}") { isTransitive = false }
//    "implementation"("org.apache.shiro:shiro-servlet-plugin:${project.extra["shiroVersion"]}")
//    "implementation"("org.apache.shiro:shiro-ehcache:${project.extra["shiroVersion"]}")
    //string interpolation in shiro
//    "runtimeOnly"("org.apache.commons:commons-configuration2:${extra["apacheConfigurationVersion"]}")
}
