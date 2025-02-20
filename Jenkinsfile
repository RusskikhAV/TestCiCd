pipeline {
    agent any

    stages {
        stage('10,12,14 * * * *') {
        steps {
            echo 'Запускаем тесты каждую 2, 4, 6-ю минуты'
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
