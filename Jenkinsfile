pipeline {
    agent any

    stages {

        stage('Preparation') {
            steps {
                script {
                    def containerName = 'my-container'
                    def imageName = 'wmhillock/mywaytask-wmhillock:latest'

                    echo "Stopping container: ${containerName}"
                    script {
                        try {
                            bat "docker container stop ${containerName}"
                        } catch (Exception e) {
                            echo "Container ${containerName} is not running or does not exist."
                        }
                    }

                    echo "Removing container: ${containerName}"
                    script {
                        try {
                            bat "docker rm ${containerName}"
                        } catch (Exception e) {
                            echo "Container ${containerName} does not exist."
                        }
                    }

                    echo "Removing image: ${imageName}"
                    script {
                        try {
                            bat "docker rmi ${imageName}"
                        } catch (Exception e) {
                            echo "Image ${imageName} does not exist."
                        }
                    }
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
