pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    def output = sh(returnStdout: true, script: 'set -x; mvn clean package')
                    echo output
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
