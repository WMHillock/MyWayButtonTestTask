pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Шаг сборки проекта, например, с помощью Maven
                    sh 'mvn clean package'
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
