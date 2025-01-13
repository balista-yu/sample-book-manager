import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.qqviaja.gradle.MybatisGenerator") version "2.5"
	id("io.gitlab.arturbosch.detekt").version("1.23.7")
	kotlin("jvm") version "2.0.10"
	kotlin("plugin.spring") version "2.0.10"
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

detekt {
	buildUponDefaultConfig = true
	allRules = false
	config.setFrom("$projectDir/config/detekt/detekt.yaml")
	autoCorrect = true
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
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("redis.clients:jedis")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.assertj:assertj-core:3.27.2")
	testImplementation("org.mockito:mockito-core:5.15.2")
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-libraries:1.23.7")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-ruleauthors:1.23.7")
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

tasks.withType<KotlinJvmCompile>().configureEach {
	compilerOptions {
		freeCompilerArgs.add("-Xjsr305=strict")
		jvmTarget.set(JvmTarget.JVM_21)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
	mainClass.set("com.book.manager.SampleBookManagerApplication")
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	doNotTrackState("Temporary workaround for Gradle state tracking issue")
}

configurations {
	mybatisGenerator
}

mybatisGenerator {
	verbose = true
	configFile = "src/main/resources/generatorConfig.xml"
}
