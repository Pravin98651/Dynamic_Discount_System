pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'discount-microservice'
        DOCKER_TAG = "v${env.BUILD_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build & Test') {
            steps {
                bat 'mvn clean test package'
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    bat "docker build -t ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} ."
                    bat "docker tag ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} ${env.DOCKER_IMAGE}:latest"
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Assuming kubectl is configured on Jenkins worker
                    bat "kubectl apply -f k8s/deployment.yaml"
                    bat "kubectl apply -f k8s/service.yaml"
                }
            }
        }
    }
}
