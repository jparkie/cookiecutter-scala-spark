#!/bin/bash

echo "[cookiecutter-scala-spark][post_gen_project.sh]: Starting...."

# 1. Initialize Git Repository
echo "[cookiecutter-scala-spark][post_gen_project.sh]: Setting up git repository...."
git init
git add .gitignore

# 2. Install pre-commit
echo "[cookiecutter-scala-spark][post_gen_project.sh]: Setting up pre-commit...."
make init

echo "[cookiecutter-scala-spark][post_gen_project.sh]: Done."