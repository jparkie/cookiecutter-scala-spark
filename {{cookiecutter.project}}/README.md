# {{cookiecutter.project}}

{{cookiecutter.description}}

## Build

1. This project is managed with [Docker](https://www.docker.com/) and [sbt](https://www.scala-sbt.org/).
2. Useful project commands are `make` targets.
3. Any of the `make` targets can be executed to initialize the Docker environment.

### Makefile

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

### Default Recommended Libraries

- **Apache Spark**: https://github.com/apache/spark - Apache Spark
- **Spark Avro**: https://github.com/databricks/spark-avro - Apache Avro
- **spark-testing-base**: https://github.com/holdenk/spark-testing-base - Spark Testing Framework
- **ScalaTest**: https://github.com/scalatest/scalatest - Scala Testing Framework
- **ScalaCheck**: https://github.com/rickynils/scalacheck - Property-Based Testing
- **Mockito**: https://github.com/mockito/mockito - Mocking Framework
- **Testcontainers**: https://github.com/testcontainers/testcontainers-scala - Docker-Based Testing
- **Ratatool**: https://github.com/spotify/ratatool - Fuzzier for Avro, Parquet, and BigTable
- **circe**: https://github.com/circe/circe-yaml - YAML Parser
- **MacWire**: https://github.com/adamw/macwire - Compile-Time Dependency Injection
- **scopt**: https://github.com/scopt/scopt - CLI Parser

## Credits

This package was created with [Cookiecutter](https://github.com/audreyr/cookiecutter) and the [jparkie/cookiecutter-scala-spark](https://github.com/jparkie/cookiecutter-scala-spark) project template.
