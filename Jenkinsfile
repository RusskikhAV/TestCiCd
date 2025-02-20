pipeline {
    agent any
    stages {
        stage('periodically triggers') {
            steps {
                echo 'Run tests'
                bat "mvn clean test"
            }
        }
        stage('Allure Report') {
            steps {
                echo 'create allure reports'
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
