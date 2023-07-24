pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    try {
                        echo 'Starting Build stage'
                        sh './mvn clean -e'
                        echo 'Maven clean completed'
                        sh './mvn package'
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
                    docker.image('wmhillock/mywaytask-wmhillock:latest').run('-d')
                    echo 'Container deployed'
                }
            }
        }
    }
}
