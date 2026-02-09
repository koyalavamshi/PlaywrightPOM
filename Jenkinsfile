pipeline {
    agent any

    environment {
        // Fix Playwright EPERM issue on Windows (SYSTEM user)
        PLAYWRIGHT_BROWSERS_PATH = "${env.WORKSPACE}\\ms-playwright"
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Verify Project') {
            steps {
                bat 'dir'
                bat 'dir pom.xml'
            }
        }

        stage('Run Playwright Java Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
