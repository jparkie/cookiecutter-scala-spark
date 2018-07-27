pipeline {
	agent none
	stages {
		stage('Checkout') {
			agent any
			steps {
				checkout scm
			}
		}
		stage('Pre-Commit') {
			agent { dockerfile {} }
			steps {
				sh 'make pre-commit'
			}
		}
		stage('Clean') {
			agent { dockerfile {} }
			steps {
				sh 'make clean'
			}
		}
		stage('Build') {
			agent { dockerfile {} }
			steps {
				sh 'make compile'
			}
		}
		stage('Unit Test') {
			agent { dockerfile {} }
			steps {
				sh 'make test-unit'
			}
		}
		stage('Integration Test') {
			agent { dockerfile {} }
			steps {
				sh 'make test-integration'
			}
		}
		stage('Acceptance Test') {
			agent { dockerfile {} }
			steps {
				sh 'make test-acceptance'
			}
		}
		stage('Coverage') {
			agent { dockerfile {} }
			steps {
				sh 'make coverage-report'
			}
		}
		stage('Assemby') {
			agent { dockerfile {} }
			steps {
				sh 'make assembly'
			}
		}
	}
}
