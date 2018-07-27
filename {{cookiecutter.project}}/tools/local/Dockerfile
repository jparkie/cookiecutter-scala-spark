FROM openjdk:8-alpine

LABEL maintainer="jparkie <https://github.com/jparkie>"
LABEL description="A Docker image used for deploying a local Apache Spark cluster"

ENV SPARK_HOME /opt/spark
ENV SPARK_MIRROR http://apache.claz.org
ENV SPARK_VERSION {{cookiecutter.spark_version}}
ENV HADOOP_VERSION 2.7
ENV PATH $PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin

# Install Build Dependencies:
RUN apk add --no-cache \
  bash \
  tar \
  wget

# Install Apache Spark:
RUN mkdir --parents /opt \
  | wget --quiet --output-document - $SPARK_MIRROR/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION.tgz \
  | tar --extract --gzip --file - --directory /opt \
  && ln -sf /opt/spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION /opt/spark \
  && chown --recursive root:root /opt/spark

WORKDIR /root

EXPOSE 4040 6066 7077 8080 8081