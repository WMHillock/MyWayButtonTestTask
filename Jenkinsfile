pipeline {
    agent any

    stages {

        stage('Preparation') {
            steps {
                script {
                    def containerName = 'my-container' // Здесь задайте имя контейнера, который нужно остановить и удалить
                    def imageName = 'wmhillock/mywaytask-wmhillock:latest' // Здесь задайте имя образа, который нужно удалить

                    echo "Stopping container: ${containerName}"
                    bat "docker stop ${containerName}"

                    echo "Stopping image: ${imageName}"
                    bat "docker stop ${imageName}"

                    echo "Removing container: ${containerName}"
                    bat "docker rm ${containerName}"

                    echo "Removing image: ${imageName}"
                    bat "docker rmi ${imageName}"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    try {
                        echo 'Starting Build stage'
                        bat 'mvn clean'
                        echo 'Maven clean completed'
                        bat 'mvn package'
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
                    docker.build('wmhillock/mywaytask-wmhillock:latest', '.')
                    echo 'Docker image built'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def containerName = 'my-container'
                    cho "Starting Deploy stage for container: ${containerName}"
                    docker.image('wmhillock/mywaytask-wmhillock:latest').run("--name ${containerName} -p 9001:8080 -d")
                    echo "Container '${containerName}' deployed"
                        }
                    }
                }
    }
}
