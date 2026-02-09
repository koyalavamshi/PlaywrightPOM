pipeline {
    agent any
    environment {
        PLAYWRIGHT_BROWSERS_PATH = "${env.WORKSPACE}/ms-playwright-browsers"
    }
    stages {
        stage('Install Dependencies') {
            steps {
                bat 'npm install'  // or relevant install command
            }
        }
        stage('Run Playwright Tests') {
            steps {
                bat 'npx playwright install chromium'
                bat 'npx playwright test'
            }
        }
    }
}
