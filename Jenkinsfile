pipeline {
    agent any
    stages {
        stage('Run tests with API tags') {
            steps {
                echo 'Run tests'
                bat "mvn clean install"
                bat "mvn test -Dgroups='Api'"
            }
        }
        stage('Example Test') {
            steps {
                echo 'Allure reports'
                bat 'allure serve'
            }
        }
    }
}
