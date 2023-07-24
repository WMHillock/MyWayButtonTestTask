pipeline {
    agent { docker { image 'maven:latest' } }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Dockerize') {
            steps {
                docker.build('wmhillock/mywaytask-wmhillock:latest', '.')
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -p 9001:8080 wmhillock/mywaytask-wmhillock:latest'
            }
        }
    }
}
