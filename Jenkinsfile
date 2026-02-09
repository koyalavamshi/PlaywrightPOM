pipeline {
    agent any

    tools {
        jdk 'Java25'
        maven 'Maven3'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/screenshots/*', allowEmptyArchive: true
            }
        }
    }
}
