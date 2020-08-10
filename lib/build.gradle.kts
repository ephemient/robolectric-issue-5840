plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(29)
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13")
    testImplementation("org.robolectric:robolectric:4.3.1")
}

val agent by configurations.creating
dependencies {
    agent(project(":agent"))
}
val agentJar = agent.incoming.artifactView {
    componentFilter { it is ProjectComponentIdentifier }
}.files
tasks.withType<Test> {
    inputs.files(agentJar)
    doFirst {
        jvmArgs("-javaagent:${agentJar.singleFile}")
    }
    testLogging.showStandardStreams = true
}
