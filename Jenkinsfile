pipeline {
    agent any

    stages {

        stage('Preparation') {
            steps {
                script {
                    def containerName = 'my-container'
                    def imageName = 'wmhillock/mywaytask-wmhillock:latest'

                    echo "Stopping container: ${containerName}"
                    bat "docker stop ${containerName}"

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
                    echo "Starting Deploy stage for container: ${containerName}"
                    docker.image('wmhillock/mywaytask-wmhillock:latest').run("-p 9001:8080 --name ${containerName} -d")
                    echo "Container '${containerName}' deployed"
                }
            }
        }
    }
}
