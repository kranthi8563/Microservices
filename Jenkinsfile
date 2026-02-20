pipeline {
    agent any

    environment {
        BUILD_VERSION = "${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking out source code..."
                git branch: 'main',
                    url: 'https://github.com/<your-username>/microservices-project.git'
            }
        }

        stage('Build user-service') {
            steps {
                dir('user-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t user-service:${BUILD_VERSION} ."
                    sh "minikube image load user-service:${BUILD_VERSION}"
                    sh "kubectl set image deployment/user-service user-service=user-service:${BUILD_VERSION}"
                    sh "kubectl rollout status deployment/user-service"
                }
            }
        }

        stage('Build product-service') {
            steps {
                dir('product-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t product-service:${BUILD_VERSION} ."
                    sh "minikube image load product-service:${BUILD_VERSION}"
                    sh "kubectl set image deployment/product-service product-service=product-service:${BUILD_VERSION}"
                    sh "kubectl rollout status deployment/product-service"
                }
            }
        }

        stage('Build order-service') {
            steps {
                dir('order-service') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t order-service:${BUILD_VERSION} ."
                    sh "minikube image load order-service:${BUILD_VERSION}"
                    sh "kubectl set image deployment/order-service order-service=order-service:${BUILD_VERSION}"
                    sh "kubectl rollout status deployment/order-service"
                }
            }
        }

        stage('Build api-gateway') {
            steps {
                dir('api-gateway') {
                    sh 'mvn clean package -DskipTests'
                    sh "docker build -t api-gateway:${BUILD_VERSION} ."
                    sh "minikube image load api-gateway:${BUILD_VERSION}"
                    sh "kubectl set image deployment/api-gateway api-gateway=api-gateway:${BUILD_VERSION}"
                    sh "kubectl rollout status deployment/api-gateway"
                }
            }
        }
    }

    post {
        success {
            echo "All services deployed successfully 🚀"
        }
        failure {
            echo "Deployment failed ❌"
        }
    }
}
