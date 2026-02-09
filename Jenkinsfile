pipeline {
    agent any

    tools {
        jdk 'Java25'       // Use the name you configured in Jenkins
        maven 'Maven3'     // Make sure Maven3 is configured in Jenkins
    }

    environment {
        // Optional: Add PATH to Playwright if needed
        PW_CLI = "${tool 'Java25'}"  // Example if you need Java path
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                echo '🔨 Building the project'
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                echo '🌐 Installing Playwright browsers'
                // Use the Playwright CLI via Maven exec plugin
                bat 'mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                echo '🚀 Running Playwright tests'
                bat 'mvn test'
            }
        }

        stage('Archive Test Results') {
            steps {
                echo '📁 Archiving test results and screenshots'
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/screenshots/*', allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo '✅ Playwright tests completed successfully!'
        }
        failure {
            echo '❌ Playwright tests failed.'
        }
        always {
            echo 'Pipeline finished.'
        }
    }
}
