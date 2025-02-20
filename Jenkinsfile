pipeline {
    agent any
    stages {
        stage('Run tests with API tag') {
            steps {
                echo 'Run tests'
                bat "mvn test -Dgroups='Api'"
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
