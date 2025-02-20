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
Сам тест из себя ничего не представляет, просто get запрос
```
pipeline {
    agent any
    stages {
        stage('Run tests with API tags') {
            steps {
                echo 'Run tests'
                bat 'mvn test -Dgroups='Api''
            }
        }
        stage('Example Test') {
            steps {
                echo 'Allure reports'
                bat 'allure -serve'
            }
        }
    }
}
```