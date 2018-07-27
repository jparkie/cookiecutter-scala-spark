/**
  * Organization:
  */
organization     := "{{cookiecutter.organization}}"
organizationName := "{{cookiecutter.organizationName}}"

/**
  * Library Meta:
  */
name := "benchmarks"

/**
  * Scala:
  */
scalaVersion := "2.11.12"

/**
  * JMH:
  */
enablePlugins(JmhPlugin)
