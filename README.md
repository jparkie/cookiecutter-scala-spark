# cookiecutter-scala-spark

Cookiecutter template for a Scala Apache Spark application.

## Requirements

### cookiecutter

Please follow instructions at https://cookiecutter.readthedocs.io/en/latest/installation.html.

#### pip

```
pip install cookiecutter
```

#### Homebrew (macOS)

```
brew install cookiecutter
```
#### Ubuntu

```
sudo apt-get install cookiecutter
```

## Installation

Please execute the following command to create a new Scala Apache Spark project.

```
cookiecutter git@github.com:jparkie/cookiecutter-scala-spark.git
```

### Configurations

```
> cookiecutter git@github.com:jparkie/cookiecutter-scala-spark.git
project [example]:
version [0.1.0-SNAPSHOT]:
organization [com.github.jparkie]:
organizationName [jparkie]:
description [TODO]:
scala_version [2.11.12]:
spark_version [2.3.1]:
spark_testing_base_version [0.10.0]:
```

## Features

### Dockerfile + Makefile + Jenkinsfile + docker-compose + JMH

- A Dockerfile with Java 8, Scala 2.11, sbt 1.1, scalafmt 1.5.1, scalastyle 1.0.0, and pre-commit is provided to self-contain the project.
- A Makefile is provided to execute various project commands within a Docker container.
- A Jenkinsfile is provided with the following stages: Checkout, Pre-Commit, Clean, Build, Unit Test, Integration Test, Acceptance Test, Coverage, and Assembly.
- A `docker-compose.yml` file is provided in `tools/local` to launch a standalone Apache Spark cluster.
- A `benchmarks` folder is provided to run JMH benchmarks.

#### Makefile

```
> make help
Welcome to {{cookiecutter.project}}!

This project is preferably managed with Docker. Please have Docker installed.

Otherwise, please install pre-commit, sbt, scalafmt, and scalastyle.

Please source project/use-local-sbt.sh if you want the Makefile to use the local sbt
Please source project/use-docker-sbt.sh if you want the Makefile to use the Docker sbt

    uninstall-docker
        Remove Docker image sbt_environment_11
    init
        Initialize project directory
    docker
        Start an interactive, Dockerized bash session
    assembly
        Assemble a fat JAR
        See https://github.com/sbt/sbt-assembly for more information
    benchmark
        Benchmark the Scala project
        See https://github.com/ktoso/sbt-jmh for more information
    clean
        Clean the Scala project
    compile
        Compile the Scala project
    coverage-report
        Generate test coverage reports
        See https://github.com/scoverage/sbt-scoverage for more information
    dependency-tree
        Generate an ASCII tree of the Scala project's dependencies
        See https://github.com/jrudolph/sbt-dependency-graph for more information
    format
        Format the Scala project's code
        See https://scalameta.org/scalafmt/ for more information
    lint
        Lint the Scala project's code
        See http://www.scalastyle.org/sbt.html for more information
    pre-commit
        Execute pre-commit hooks
        See https://pre-commit.com/ for more information
    release
        Verify the Scala project to version and release the code to git
        See https://github.com/sbt/sbt-release for more information
    test-all
        Execute all of the tests in the Scala project
    test-*-only TEST_ONLY=**
        Execute only the unit/integration/acceptance tests that match the wildcard in TEST_ONLY
    test-unit
        Execute the tests in the Scala project
    test-integration
        Execute the itests in the Scala project
    test-acceptance
        Execute the atests in the Scala project
```

### Utility Classes

To promote good practices, the project provides the following utilities.
- [LogsProxy.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/main/scala/example/util/LogsProxy.scala)
- [MetricsProxy.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/main/scala/example/util/MetricsProxy.scala)
- [PerJvmReference.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/main/scala/example/util/PerJvmReference.scala)

### Testing Classes

To promote good testing practices, the project provides the following classes.
- [BaseSuiteLike.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/test/scala/suites/BaseSuiteLike.scala)
- [SparkUnitSuite.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/test/scala/suites/SparkUnitSuite.scala)
- [SparkIntegrationSuite.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/itest/scala/suites/SparkIntegrationSuite.scala)
- [SparkAcceptanceSuite.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/atest/scala/suites/SparkAcceptanceSuite.scala)
- [GlobalFixtures.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/test/scala/fixtures/GlobalFixtures.scala)
- [ConsoleCapture.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/test/scala/testutil/ConsoleCapture.scala)
- [LoggingCapture.scala](https://github.com/jparkie/cookiecutter-scala-spark/blob/master/%7B%7Bcookiecutter.project%7D%7D/src/test/scala/testutil/LoggingCapture.scala)

### Project Structure

The SBT project is organized with `src/main/scala` for code, `src/test/scala` for unit tests, `src/itest/scala` for integration tests, `src/atest/scala` for acceptance tests, and `benchmarks/src` for microbenchmarks.

```
> tree -a ./
./
├── .dockerignore
├── .github
│   └── PULL_REQUEST_TEMPLATE.md
├── .gitignore
├── .pre-commit-config.yaml
├── Dockerfile
├── Jenkinsfile
├── Makefile
├── README.md
├── benchmarks
│   ├── build.sbt
│   └── src
│       └── main
│           └── scala
│               └── benchmarks
│                   ├── ExampleBenchmark.scala
│                   └── package.scala
├── build.sbt
├── project
│   ├── assembly.sbt
│   ├── build.properties
│   ├── plugins.sbt
│   ├── use-docker-sbt.sh
│   └── use-local-sbt.sh
├── scalastyle-config.xml
├── src
│   ├── atest
│   │   ├── resources
│   │   │   └── TODO
│   │   └── scala
│   │       └── suites
│   │           └── SparkAcceptanceSuite.scala
│   ├── itest
│   │   ├── resources
│   │   │   └── TODO
│   │   └── scala
│   │       └── suites
│   │           └── SparkIntegrationSuite.scala
│   ├── main
│   │   ├── resources
│   │   │   └── TODO
│   │   └── scala
│   │       ├── example
│   │       │   ├── package.scala
│   │       │   └── util
│   │       │       ├── LogsProxy.scala
│   │       │       ├── MetricsProxy.scala
│   │       │       ├── PerJvmReference.scala
│   │       │       └── package.scala
│   │       └── org
│   │           └── apache
│   │               └── spark
│   │                   └── metrics
│   │                       └── source
│   │                           └── PublicSource.scala
│   └── test
│       ├── resources
│       │   └── TODO
│       └── scala
│           ├── fixtures
│           │   ├── GlobalFixtures.scala
│           │   └── package.scala
│           ├── org
│           │   └── apache
│           │       └── spark
│           │           └── util
│           │               └── PublicClosureCleaner.scala
│           ├── suites
│           │   ├── BaseSuiteLike.scala
│           │   ├── SparkUnitSuite.scala
│           │   └── package.scala
│           └── testutil
│               ├── ConsoleCapture.scala
│               ├── LoggingCapture.scala
│               └── package.scala
├── tools
│   └── local
│       ├── Dockerfile
│       ├── Makefile
│       ├── conf
│       │   ├── master
│       │   │   └── TODO
│       │   └── worker
│       │       └── TODO
│       ├── data
│       │   ├── master
│       │   │   └── TODO
│       │   └── worker
│       │       └── TODO
│       ├── docker-compose.yml
│       └── workspace
│           └── TODO
└── version.sbt

45 directories, 48 files
```
