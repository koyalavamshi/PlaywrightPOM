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
stage('Locate POM') {
    steps {
        bat 'dir /s pom.xml'
    }
}
        stage('Run Playwright Tests') {
            steps {
                dir('PlaywrightPOM') {   // 👈 folder containing pom.xml
                    bat 'mvn clean test'
                }
            }
        }
    }
}
