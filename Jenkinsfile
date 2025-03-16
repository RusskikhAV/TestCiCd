pipeline {
    agent any
    environment {
        login = credentials('login')
        password = credentials('password')
    }
    stages {
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
