#!/bin/bash

set -euo pipefail
IFS=$'\n\t'

#
# Functions:
#

function echo_info() {
	echo "[cookiecutter-scala-spark] INFO post_gen_project.sh - $1" >&1
}


#
# Step 1:
#

echo_info "Initializing Git"
git init
git add .
git commit -n -m "Initial Commit"

#
# Step 2:
#

echo_info "Initializing Project"
make init

#
# Done:
#

echo_info "Done!"
