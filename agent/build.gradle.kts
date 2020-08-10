plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.ow2.asm:asm:8.0.1")
    implementation("org.ow2.asm:asm-util:8.0.1")
}

tasks.named<Jar>("jar") {
    manifest.attributes(
        "Class-Path" to configurations.runtimeClasspath.get().files.joinToString(" "),
        "Agent-Class" to "a.agent.Main",
        "Premain-Class" to "a.agent.Main"
    )
}
