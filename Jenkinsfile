pipeline {
    agent any
    stages {
        stage('Clean and Install') {
            steps {
                 echo 'Run clean and install'
                 bat "mvn clean install"
            }
        }
        stage('Run tests with API tags') {
            steps {
                echo 'Run tests'
                bat "mvn clean install"
                bat "mvn test -Dgroups='Api'"
            }
        }
        stage('Allure Report') {
            steps {
                echo 'allure reports'
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
