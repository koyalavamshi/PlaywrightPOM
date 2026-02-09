pipeline {
    agent any
    environment {
        PLAYWRIGHT_BROWSERS_PATH = "${env.WORKSPACE}/ms-playwright-browsers"
    }
    stages {
        stage('Install Dependencies') {
            steps {
                sh 'npm install'  // or relevant install command
            }
        }
        stage('Run Playwright Tests') {
            steps {
                sh 'npx playwright install chromium'
                sh 'npx playwright test'
            }
        }
    }
}
