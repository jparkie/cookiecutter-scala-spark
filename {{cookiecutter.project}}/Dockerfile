FROM openjdk:8-alpine

LABEL maintainer="jparkie <https://github.com/jparkie>"
LABEL description="A Docker image used for building cookiecutter-scala-spark projects"

ENV SBT_HOME /usr/local/sbt
ENV SBT_VERSION 1.1.6
ENV SCALA_HOME /usr/local/scala
ENV SCALA_BINARY_VERSION 2.11
ENV SCALA_VERSION 2.11.12
ENV SCALAFMT_VERSION 1.5.1
ENV SCALASTYLE_VERSION 1.0.0
ENV PATH $PATH:$SCALA_HOME/bin:$SBT_HOME/bin:/root/bin/

# Install Build Dependencies:
RUN apk add --no-cache \
  bash \
  curl \
  git \
  make \
  python \
  tar

# Install Scala:
RUN curl --fail --silent --location https://downloads.lightbend.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz \
  | tar --extract --gzip --file - --directory /usr/local --strip-components 1 \
  && scala -version

# Install SBT:
RUN curl --fail --silent --location https://piccolo.link/sbt-$SBT_VERSION.tgz \
  | tar --extract --gzip --file - --directory /usr/local \
  && sbt sbtVersion

# Install coursier:
RUN curl --fail --silent --output coursier --location https://git.io/vgvpD \
  && chmod +x coursier \
  && mv coursier /usr/local/bin

# Install scalafmt:
RUN coursier bootstrap --repository bintray:scalameta/maven --output /usr/local/bin/scalafmt --standalone --main org.scalafmt.cli.Cli com.geirsson:scalafmt-cli_$SCALA_BINARY_VERSION:$SCALAFMT_VERSION \
  && scalafmt --version

# Install scalastyle:
RUN coursier bootstrap --repository https://oss.sonatype.org/content/repositories/snapshots --output /usr/local/bin/scalastyle --standalone --main org.scalastyle.Main org.scalastyle:scalastyle_$SCALA_BINARY_VERSION:$SCALASTYLE_VERSION

# Install pre-commit:
RUN curl --fail --silent https://pre-commit.com/install-local.py | python -

# Configure Makefile:
ENV COOKIECUTTER_SCALA_SPARK_RUN_PREREQUISITES ""
ENV COOKIECUTTER_SCALA_SPARK_RUN ""

WORKDIR /repo