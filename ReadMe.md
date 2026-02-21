🚀 Microservices CI/CD with Kubernetes & Jenkins
📌 Project Overview

This project demonstrates a complete end-to-end DevOps implementation of a Spring Boot microservices architecture deployed on Kubernetes with a fully automated CI/CD pipeline using Jenkins and Docker Hub.

The goal of this project was to explore:

Microservices architecture

Containerization with Docker

Kubernetes orchestration

API Gateway routing

PostgreSQL integration

CI/CD automation with Jenkins

Artifact management using Docker Hub

🏗️ Architecture
Client
   ↓
API Gateway (Spring Cloud Gateway)
   ↓
-----------------------------------
|  user-service                  |
|  product-service               |
|  order-service                 |
-----------------------------------
   ↓
PostgreSQL (ClusterIP)
🧩 Microservices
1️⃣ User Service

Manages users

CRUD operations

Connected to PostgreSQL

2️⃣ Product Service

Manages products

REST-based CRUD

Persistent storage via PostgreSQL

3️⃣ Order Service

Manages orders

References userId and productId

Database persistence

4️⃣ API Gateway

Central entry point

Spring Cloud Gateway (Reactive)

Routes requests to internal services

Enables unified access

🐳 Containerization

Each service:

Built using Maven

Dockerized using multi-stage Dockerfile

Pushed to Docker Hub:

kranthireddy8563/user-service
kranthireddy8563/product-service
kranthireddy8563/order-service
kranthireddy8563/api-gateway
☸️ Kubernetes Deployment

Cluster: Minikube (IPVS mode)

Kubernetes objects used:

Deployments

Services (ClusterIP)

Secrets

Persistent Volume

Persistent Volume Claim

Key Concepts Explored:

Service-to-service communication

DNS-based service discovery

Rolling updates

ImagePullPolicy management

Pod lifecycle handling

Troubleshooting networking latency (IPVS vs iptables)

🔁 CI/CD Pipeline (Jenkins)

Pipeline Flow:

GitHub Push
    ↓
Jenkins Pipeline
    ↓
Maven Build
    ↓
Docker Build
    ↓
Docker Push (Docker Hub)
    ↓
kubectl set image
    ↓
Rolling Deployment Update
CI/CD Features Implemented:

Pipeline as Code (Jenkinsfile)

Docker Hub authentication via credentials

Automated image tagging using BUILD_NUMBER

Rolling deployment updates

Deployment status verification

Production-style image pulling (imagePullPolicy: Always)

🔐 Artifact Registry

Artifact Registry Used:

Docker Hub

Images are versioned and automatically pushed during Jenkins builds.

🧪 How to Access the Application
Option 1: Port Forward
kubectl port-forward deployment/api-gateway 9000:8080

Access:

http://localhost:9000/users
http://localhost:9000/products
http://localhost:9000/orders

🛠️ Technologies Used

Java 17

Spring Boot

Spring Data JPA

Spring Cloud Gateway

PostgreSQL

Docker

Kubernetes (Minikube)

Jenkins

Docker Hub

GitHub

🧠 Key Learnings

Through this project, I explored:

Designing microservices architecture

Debugging Kubernetes networking (DNS, IPVS, kube-proxy)

Handling imagePullPolicy issues

Managing Docker image versioning

Resolving Jenkins permission conflicts

Secure credential management in CI/CD

Automating full deployment lifecycle

Production-style artifact management

📈 Future Enhancements

Add Ingress Controller

Add Liveness & Readiness Probes

Add Blue-Green Deployment

Add Automated Rollback

Integrate Prometheus & Grafana

Deploy to Cloud (AWS EKS / Azure AKS)

🏁 Conclusion

This project demonstrates a complete DevOps lifecycle:

Microservices development

Containerization

Orchestration

CI/CD automation

Production-grade deployment strategy

It reflects hands-on experience with real-world DevOps practices and Kubernetes-based microservice deployment.