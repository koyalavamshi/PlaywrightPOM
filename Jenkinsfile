pipeline {
    agent any

    tools {
        maven 'Maven3'   // Maven tool configured in Jenkins
        jdk 'Java17'     // Your Java version
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Locate POM') {
            steps {
                // Optional: just to verify pom.xml exists
                bat 'dir /s pom.xml'
            }
        }

        stage('Build Project') {
            steps {
                // Compile the project and download dependencies
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                // Required for Playwright Java
                bat 'mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                // Run your Playwright tests via Maven
                bat 'mvn test'
            }
        }

        stage('Publish Test Reports') {
            steps {
                // Optional: if you have Surefire or ReportNG reports
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/surefire-report.html', // adjust path if needed
                    reportFiles: 'index.html',
                    reportName: 'Playwright Test Report'
                ])
            }
        }
    }

    post {
        success {
            echo '✅ Playwright tests passed!'
        }
        failure {
            echo '❌ Playwright tests failed!'
        }
        always {
            echo 'Pipeline finished'
        }
    }
}
