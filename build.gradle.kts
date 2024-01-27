plugins {
    kotlin("jvm") version "1.8.22"
    id("com.github.weave-mc.weave-gradle") version "649dba7468"
}

group = "me.lily"
version = "1.1.3"

minecraft.version("1.8.9")

repositories {
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:v0.2.5")

    compileOnly("org.spongepowered:mixin:0.8.5")
}

tasks.compileJava {
    options.release.set(17)
}
