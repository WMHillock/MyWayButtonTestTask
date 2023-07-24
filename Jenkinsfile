pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    try {
                        sh 'mvn clean'
                        sh 'mvn package'
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
                    echo 'Starting Dockerize stage'
                    // Шаг создания Docker-образа из собранного артефакта
                    docker.build('wmhillock/mywaytask-wmhillock:latest', '.')
                    echo 'Docker image built'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Starting Deploy stage'
                    // Шаг запуска контейнера из созданного образа
                    docker.image('wmhillock/mywaytask-wmhillock:latest').run('-p 9001:8080 -d')
                    echo 'Container deployed'
                }
            }
        }
    }
}
