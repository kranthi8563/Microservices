pipeline {
    agent any

    environment {
        DOCKER_USERNAME = "kranthireddy8563"
        BUILD_VERSION = "${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/kranthi8563/Microservices.git'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Build & Push user-service') {
            steps {
                dir('user-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t $DOCKER_USERNAME/user-service:$BUILD_VERSION ."
                    sh "docker push $DOCKER_USERNAME/user-service:$BUILD_VERSION"
                    sh "kubectl set image deployment/user-service user-service=$DOCKER_USERNAME/user-service:$BUILD_VERSION"
                    sh "kubectl rollout status deployment/user-service"
                }
            }
        }

        stage('Build & Push product-service') {
            steps {
                dir('product-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t $DOCKER_USERNAME/product-service:$BUILD_VERSION ."
                    sh "docker push $DOCKER_USERNAME/product-service:$BUILD_VERSION"
                    sh "kubectl set image deployment/product-service product-service=$DOCKER_USERNAME/product-service:$BUILD_VERSION"
                    sh "kubectl rollout status deployment/product-service"
                }
            }
        }

        stage('Build & Push order-service') {
            steps {
                dir('order-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t $DOCKER_USERNAME/order-service:$BUILD_VERSION ."
                    sh "docker push $DOCKER_USERNAME/order-service:$BUILD_VERSION"
                    sh "kubectl set image deployment/order-service order-service=$DOCKER_USERNAME/order-service:$BUILD_VERSION"
                    sh "kubectl rollout status deployment/order-service"
                }
            }
        }

        stage('Build & Push api-gateway') {
            steps {
                dir('api-gateway') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t $DOCKER_USERNAME/api-gateway:$BUILD_VERSION ."
                    sh "docker push $DOCKER_USERNAME/api-gateway:$BUILD_VERSION"
                    sh "kubectl set image deployment/api-gateway api-gateway=$DOCKER_USERNAME/api-gateway:$BUILD_VERSION"
                    sh "kubectl rollout status deployment/api-gateway"
                }
            }
        }
    }

    post {
        success {
            echo "🚀 All services built, pushed & deployed successfully!"
        }
        failure {
            echo "❌ Deployment failed."
        }
    }
}