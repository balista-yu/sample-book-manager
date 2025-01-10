plugins {
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.qqviaja.gradle.MybatisGenerator") version "2.5"
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
}

group = "com.book.manager"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.2")
	implementation("org.postgresql:postgresql:42.7.4")
	implementation("org.mybatis.generator:mybatis-generator-core:1.4.2")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
	mainClass.set("com.book.manager.sample_book_manager.SampleBookManagerApplication")
}
tasks.register<Copy>("getDependencies") {
	dependsOn("build")
	from(configurations.runtimeClasspath)
	into("runtime/")
	doFirst {
		val runtimeDir = File("runtime")
		runtimeDir.deleteRecursively()
		runtimeDir.mkdir()
	}
	doLast {
		println("Dependencies copied to runtime/")
	}
}

configurations {
	mybatisGenerator
}

mybatisGenerator {
	verbose = true
	configFile = "src/main/resources/generatorConfig.xml"
}
