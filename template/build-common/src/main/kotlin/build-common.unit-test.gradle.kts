tasks.withType<Test> {
	useJUnitPlatform {
		testLogging {
			events("passed", "skipped", "failed")
		}
	}

	reports {
		junitXml.isEnabled = false
	}

	systemProperty("junit.jupiter.execution.parallel.enabled", "true")
	systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")

	maxParallelForks = Runtime.getRuntime().availableProcessors()
}
