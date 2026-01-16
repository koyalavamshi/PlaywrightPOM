pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Playwright Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
