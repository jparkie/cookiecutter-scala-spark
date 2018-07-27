SCALE = 1
WORKER = 1

.docker/spark: Dockerfile
	docker build --tag local/spark:{{cookiecutter.spark_version}} --file Dockerfile .
	mkdir -p .docker
	touch .docker/spark

help:
	@echo "Welcome to tools/local!"
	@echo ""
	@echo "This folder stores all the Docker-related files required to launch a standalone Apache Spark cluster."
	@echo ""
	@echo "Store relevant configurations within ./conf/master or ./conf/worker"
	@echo "Store relevant data within ./data/master or ./data/worker"
	@echo "Store miscellaneous files within ./workspace"
	@echo ""
	@echo "Each Apache Spark worker has 2 cores and 1 GB of memory."
	@echo ""
	@echo "    uninstall-docker"
	@echo "        Remove Docker image local/spark:{{cookiecutter.spark_version}}"
	@echo "    up SCALE=1"
	@echo "        Start the standalone Apache Spark cluster"
	@echo "    down"
	@echo "        Stop the standalone Apache Spark cluster"
	@echo "    attach-master"
	@echo "        Attach to the master Docker container"
	@echo "    attach-worker WORKER=1"
	@echo "        Attach to the worker Docker container with index WORKER"
	@echo "    list-master"
	@echo "        Echo the master's web UI's URL"
	@echo "    list-worker WORKER=1"
	@echo "        Echo the worker's web UI's URL with index WORKER"

uninstall-docker:
	rm -rf .docker
	docker rmi --force local/spark:{{cookiecutter.spark_version}}

up: .docker/spark
	docker-compose up --scale spark-worker=$(SCALE)

down: .docker/spark
	docker-compose down

attach-master:
	docker exec --interactive --tty spark-master /bin/bash

attach-worker:
	docker exec --interactive --tty local_spark-worker_$(WORKER) /bin/bash

list-master:
	@echo "http://$$(docker-compose port spark-master 8080)"

list-worker:
	@echo "http://$$(docker-compose port --index=$(WORKER) spark-worker 8081)"

.PHONY: help uninstall-docker up down attach-master attach-worker list-master list-worker