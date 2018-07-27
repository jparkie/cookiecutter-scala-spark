/**
  * Organization:
  */
organization     := "{{cookiecutter.organization}}"
organizationName := "{{cookiecutter.organizationName}}"

/**
  * Library Meta:
  */
name := "{{cookiecutter.project}}"

/**
  * Scala:
  */
scalaVersion := "{{cookiecutter.scala_version}}"

/**
  * Java:
  */
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
javaOptions  ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

/**
  * Projects:
  */
lazy val root = project
  .in(file("."))

lazy val benchmarks = project
  .in(file("benchmarks"))
  .dependsOn(root)

/**
  * Library Dependencies:
  */

// Versions:
val CirceYamlVersion           = "0.8.0"
val MacWireVersion             = "2.3.0"
val MockitoVersion             = "1.0.0"
val RataToolVersion            = "0.3.4"
val ScalaTestVersion           = "3.0.5"
val ScalaCheckVersion          = "1.14.0"
val ScoptVersion               = "3.7.0"
val SparkVersion               = "{{cookiecutter.spark_version}}"
val SparkAvroVersion           = "4.0.0"
val SparkTestingVersion        = "{{cookiecutter.spark_version}}_{{cookiecutter.spark_testing_base_version}}"
val TestContainersScalaVersion = "0.14.0"

// Spark Dependencies:
val sparkCore    = "org.apache.spark" %% "spark-core"         % SparkVersion        % "provided"
val sparkSql     = "org.apache.spark" %% "spark-sql"          % SparkVersion        % "provided"
val sparkAvro    = "com.databricks"   %% "spark-avro"         % SparkAvroVersion
val sparkTesting = "com.holdenkarau"  %% "spark-testing-base" % SparkTestingVersion % "test"

// Test Dependencies:
val scalaTest           = "org.scalatest"  %% "scalatest"            % ScalaTestVersion           % "test"
val scalaCheck          = "org.scalacheck" %% "scalacheck"           % ScalaCheckVersion          % "test"
val mockito             = "org.mockito"    %% "mockito-scala"        % MockitoVersion             % "test"
val testContainersScala = "com.dimafeng"   %% "testcontainers-scala" % TestContainersScalaVersion % "test"
val rataToolSampling    = "com.spotify"    %% "ratatool-sampling"    % RataToolVersion            % "test"
val rataToolScalaCheck  = "com.spotify"    %% "ratatool-scalacheck"  % RataToolVersion            % "test"

// Other Dependencies:
val circeYaml = "io.circe"                 %% "circe-yaml" % CirceYamlVersion
val macWire   = "com.softwaremill.macwire" %% "macros"     % MacWireVersion   % "provided"
val scopt     = "com.github.scopt"         %% "scopt"      % ScoptVersion

// Hack Dependencies:
// Hack #1: This symbol is required by 'method org.apache.spark.metrics.MetricsSystem.getServletHandlers'.
// See: https://github.com/groupon/spark-metrics/blob/7f19cafcf4c060369f11894df999a955bfae1495/pom.xml#L244-L247
val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.4.12.v20180830" % "provided"

val sparkDependencies = Seq(sparkCore, sparkSql, sparkAvro, sparkTesting)
val testDependencies  = Seq(scalaTest, scalaCheck, mockito, testContainersScala, rataToolSampling, rataToolScalaCheck)
val otherDependencies = Seq(circeYaml, macWire, scopt)
val hacksDependencies = Seq(jettyServlet)

libraryDependencies ++= (sparkDependencies ++ testDependencies ++ otherDependencies ++ hacksDependencies)
  .map(configureProvidedModuleID)

/**
  * Tests:
  */
// Unit Tests:
parallelExecution in Test := true
fork              in Test := true

// Integration Tests:
lazy val ScalaIntegrationTest = config("itest") extend Test
// Register itest Configuration
configs(ScalaIntegrationTest)
// Initialize itest with Defaults.testSettings
inConfig(ScalaIntegrationTest)(Defaults.testSettings)
// Configure ScalaIntegrationTest:
parallelExecution in ScalaIntegrationTest := false
fork              in ScalaIntegrationTest := true
scalaSource       in ScalaIntegrationTest := baseDirectory.value / "src" / "itest" / "scala"
resourceDirectory in ScalaIntegrationTest := baseDirectory.value / "src" / "itest" / "resources"

// Acceptance Tests:
lazy val ScalaAcceptanceTest = config("atest") extend Test
// Register atest Configuration
configs(ScalaAcceptanceTest)
// Initialize atest with Defaults.testSettings
inConfig(ScalaAcceptanceTest)(Defaults.testSettings)
// Configure ScalaAcceptanceTest:
parallelExecution in ScalaAcceptanceTest := false
fork              in ScalaAcceptanceTest := true
scalaSource       in ScalaAcceptanceTest := baseDirectory.value / "src" / "atest" / "scala"
resourceDirectory in ScalaAcceptanceTest := baseDirectory.value / "src" / "atest" / "resources"

/**
  * Format:
  */
inConfig(ScalaIntegrationTest)(ScalafmtPlugin.scalafmtConfigSettings)

inConfig(ScalaAcceptanceTest)(ScalafmtPlugin.scalafmtConfigSettings)

/**
  * Lint:
  */
scalastyleFailOnWarning := true

/**
  * Coverage:
  */
coverageEnabled in Test := true

coverageEnabled in ScalaIntegrationTest := true

coverageEnabled in ScalaAcceptanceTest := true

/**
  * Assembly:
  */
assemblyJarName in assembly := s"$name-$version.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyShadeRules in assembly := Seq()

test in assembly := {}

/**
  * Release:
  */
import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion
)

/**
  * Hacks:
  */
// Hack #1: Run Spark within IntelliJ
run in Compile := Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run)).evaluated

// Hack #2: Ensure provided dependencies are available in itest and atest.
def configureProvidedModuleID(moduleID: ModuleID): ModuleID = {
  moduleID.configurations match {
    case Some(configurations) if configurations.startsWith("provided")=>
      moduleID.withConfigurations(Some(s"$configurations,${ScalaIntegrationTest.name},${ScalaAcceptanceTest.name}"))
    case _ =>
      moduleID
  }
}
