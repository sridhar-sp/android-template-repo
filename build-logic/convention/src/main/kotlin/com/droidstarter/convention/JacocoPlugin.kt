package com.droidstarter.convention

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.droidstarter.support.AppConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.testing.jacoco.tasks.JacocoReportBase
import java.util.Locale

class JacocoPlugin : Plugin<Project> {
    @Suppress("IfThenToElvis")
    override fun apply(project: Project) {

        val jacocoMetaData = project.extensions.create("jacocoMetaData", JacocoMetaData::class.java)

        with(project) {
            pluginManager.apply("jacoco")

            afterEvaluate {
                val appExtension = extensions.findByType<AppExtension>()
                val libraryExtension = extensions.findByType<LibraryExtension>()

                if (appExtension == null && libraryExtension == null) {
                    println("Dude you've applied the JacocoPlugin in unknown AndroidComponent")
                    return@afterEvaluate
                }

                val variantList = if (appExtension != null) {
                    appExtension.applicationVariants.map { it.name }.toList()
                } else if (libraryExtension != null) {
                    libraryExtension.libraryVariants.map { it.name }.toList()
                } else emptyList<String>()

                configureJacoco(
                    coverageExclusionList = jacocoMetaData.coverageExclusionList, variantList = variantList
                )
            }

        }

    }
}

open class JacocoMetaData {
    var coverageExclusionList: List<String> = emptyList()
}

private val DEFAULT_COVERAGE_EXCLUSIONS = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
)

private fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

internal fun JacocoReportBase.setupReporting(
    project: Project, testTaskName: String, variant: String, coverageExclusionList: List<String>
) {
    with(project) {
        classDirectories.setFrom(fileTree("$buildDir/tmp/kotlin-classes/$variant") {
            exclude(DEFAULT_COVERAGE_EXCLUSIONS)
            exclude(coverageExclusionList)
        })

        sourceDirectories.setFrom(files("$projectDir/src/main/java", "$projectDir/src/main/kotlin"))
        executionData.setFrom(file("$buildDir/jacoco/$testTaskName.exec"))
    }
}

internal fun Project.configureJacoco(coverageExclusionList: List<String>, variantList: List<String>) {

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }

    val groupName = "jacoco"

    val jacocoTestReport = tasks.create("jacocoTestReport").apply { group = groupName }
    val jacocoTestVerification = tasks.create("jacocoTestVerification").apply { group = groupName }

    variantList.forEach { variant ->
        val testTaskName = "test${variant.capitalize()}UnitTest"
        val buildDir = layout.buildDirectory.get().asFile
        val reportTask = tasks.register("jacoco${testTaskName.capitalize()}Report", JacocoReport::class) {
            group = groupName
            dependsOn(testTaskName)

            reports {
                xml.required.set(true)
                html.required.set(true)
            }

            setupReporting(
                project = this@configureJacoco,
                testTaskName = testTaskName,
                variant = variant,
                coverageExclusionList = coverageExclusionList
            )
        }

        val verificationTask = tasks.register(
            "jacoco${testTaskName.capitalize()}Verification", JacocoCoverageVerification::class.java
        ) {
            group = groupName
            dependsOn(testTaskName)

            violationRules {
                rule {
                    limit {
                        minimum = AppConfig.minimumCoverageLimit
                    }
                }
            }

            setupReporting(
                project = this@configureJacoco,
                testTaskName = testTaskName,
                variant = variant,
                coverageExclusionList = coverageExclusionList
            )
        }

        jacocoTestReport.dependsOn(reportTask)
        jacocoTestVerification.dependsOn(verificationTask)
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            // TODO: Consider removing if not we don't add Robolectric
            isIncludeNoLocationClasses = true

            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}