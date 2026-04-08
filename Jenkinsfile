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
                sh 'mvn clean test package'
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t ${ENV.DOCKER_IMAGE}:${ENV.DOCKER_TAG} ."
                    sh "docker tag ${ENV.DOCKER_IMAGE}:${ENV.DOCKER_TAG} ${ENV.DOCKER_IMAGE}:latest"
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Assuming kubectl is configured on Jenkins worker
                    sh "kubectl apply -f k8s/deployment.yaml"
                    sh "kubectl apply -f k8s/service.yaml"
                }
            }
        }
    }
}
