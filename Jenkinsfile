pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    try {
                        echo 'Starting Build stage'
                                        // Шаг сборки проекта, например, с помощью Maven
                        sh 'mvn clean'
                        echo 'Maven clean completed'
                        sh 'mvn package'
                        echo 'Maven package completed'
                    } catch (Exception e) {
                        echo "Build failed: ${e.getMessage()}"
                        throw e
                    }
                }
            }
        }

        stage('Dockerize') {
            steps {
                script {
                    // Шаг создания Docker-образа из собранного артефакта
                    docker.build('wmhillock/mywaytask-wmhillock:latest', '.')
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Шаг запуска контейнера из созданного образа
                    docker.image('wmhillock/mywaytask-wmhillock:latest').run('-p 9001:8080 -d')
                }
            }
        }
    }
}
