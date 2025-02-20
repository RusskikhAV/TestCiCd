# TestCiCd
Первым этапом проверил запуск команд:
"mvn -v" и "java -v" с помощью Jenkinsfile

```Jenkinsfile
pipeline {
    agent any
        stages {
            stage('Example Build') {
                steps {
                echo 'Hello, Maven'
                bat 'mvn --version'
                }
            }
            stage('Example Test') {
                steps {
                echo 'Hello, JDK'
                bat 'java -version'
            }
        }
    }
}
```
Далее запуск теста находящегося в test\java\api\google\GoogleGetRequestTest.java
Сам тест из себя ничего не представляет, просто get запрос с параметром 
"q" = 'Google'

Запуск сбора отчётности с помощью плагина allure в Jenkins
```
pipeline {
    agent any
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
```

Триггеры