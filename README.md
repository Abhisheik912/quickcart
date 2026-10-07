# 🚀 QuickCart | End-to-End DevOps Project on Azure

QuickCart is a Java Spring Boot-based e-commerce backend application developed to demonstrate a complete **end-to-end DevOps CI/CD workflow**.

The project automates application building, testing, code quality analysis, container security scanning, Docker image management, and Kubernetes deployment using Jenkins and Microsoft Azure.

The application is containerized using Docker, stored in Azure Container Registry (ACR), and deployed to Azure Kubernetes Service (AKS).

## 🏗️ Project Architecture

```text
GitHub
   |
   v
Jenkins CI/CD Pipeline
   |
   +-- Maven Build
   |
   +-- Automated Unit Tests
   |
   +-- Docker Image Build
   |
   +-- SonarQube Analysis
   |      |
   |      +-- Quality Gate
   |
   +-- Trivy Security Scan
   |
   +-- Azure Authentication
   |
   v
Azure Container Registry (ACR)
   |
   v
Azure Kubernetes Service (AKS)
   |
   v
Kubernetes Deployment
   |
   v
Kubernetes LoadBalancer
   |
   v
QuickCart REST API
```

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| Backend | Java 21, Spring Boot |
| Build Tool | Apache Maven |
| Version Control | Git, GitHub |
| CI/CD | Jenkins |
| Code Quality | SonarQube |
| Security Scanning | Trivy |
| Containerization | Docker |
| Container Registry | Azure Container Registry |
| Container Orchestration | Kubernetes |
| Cloud Platform | Microsoft Azure |
| Cloud Deployment | Azure Kubernetes Service |
| Networking | Kubernetes LoadBalancer |
| Automation | Jenkins Pipeline, Azure CLI |

## 📂 Project Structure

```text
quickcart/
├── src/
│   ├── main/
│   │   ├── java/com/quickcart/
│   │   │   ├── controller/
│   │   │   │   ├── ProductController.java
│   │   │   │   └── OrderController.java
│   │   │   ├── model/
│   │   │   │   ├── Product.java
│   │   │   │   ├── Order.java
│   │   │   │   └── OrderRequest.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── QuickcartApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/quickcart/
├── Dockerfile
├── Jenkinsfile
├── deployment.yml
├── service.yml
├── pom.xml
└── README.md
```

## ⚙️ Application Features

QuickCart provides REST APIs for basic product and order operations.

### Product API

**Endpoint:**

```http
GET /api/products
```

**Sample Response:**

```json
[
  {
    "id": 1,
    "name": "Wireless Headphones",
    "price": 2499.0
  },
  {
    "id": 2,
    "name": "Mechanical Keyboard",
    "price": 3499.0
  },
  {
    "id": 3,
    "name": "Gaming Mouse",
    "price": 1499.0
  }
]
```

### Order API

**Endpoint:**

```http
POST /api/orders
```

**Request Body:**

```json
{
  "productId": 1,
  "quantity": 2
}
```

**Sample Response:**

```json
{
  "orderId": 1001,
  "productId": 1,
  "quantity": 2,
  "status": "CONFIRMED"
}
```

The application also implements request validation and centralized exception handling.

## 🔄 Jenkins CI/CD Pipeline

The Jenkins pipeline automates the application delivery process through the following stages.

| Stage | Description |
|---|---|
| Checkout SCM | Retrieves source code from GitHub |
| Build | Builds the application using Maven |
| Test | Executes automated tests |
| Docker Build | Creates the Docker image |
| SonarQube | Performs static code analysis |
| Trivy Scan | Scans the image for HIGH and CRITICAL vulnerabilities |
| Azure Login | Authenticates with Azure using a Service Principal |
| ACR Push | Pushes the Docker image to Azure Container Registry |
| Deploy to Azure AKS | Applies Kubernetes deployment manifests |
| Verify Deployment | Verifies Kubernetes Pods and Services |

The pipeline integrates code quality and security checks before the container image is published and deployed.

## 🔍 Code Quality Analysis

SonarQube is integrated with Jenkins to analyze application code and enforce the configured Quality Gate.

**Final analysis results:**

- Quality Gate: Passed
- New Bugs: 0
- New Vulnerabilities: 0
- Security Hotspots: 0
- New Code Smells: 0
- Reliability Rating: A
- Security Rating: A
- Maintainability Rating: A

The Jenkins pipeline stops if the configured Quality Gate fails.

## 🔐 Container Security with Trivy

Trivy scans the Docker image for HIGH and CRITICAL vulnerabilities before deployment.

The security scan is configured to fail the pipeline when vulnerabilities matching the configured severity levels are detected.

**Final scan results:**

| Scanned Component | Vulnerabilities Detected |
|---|---|
| Container Image | 0 |
| Application JAR | 0 |
| Go Binary | 0 |

During development, dependency vulnerabilities were identified and resolved by updating affected application dependencies.

## 🐳 Docker Containerization

QuickCart uses Eclipse Temurin Java 21 JRE as its container runtime.

**Dockerfile:**

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/quickcart-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build the Application

```bash
mvn clean package
```

### Build the Docker Image

```bash
docker build -t quickcart:latest .
```

### Run the Container Locally

```bash
docker run -d -p 8081:8081 --name quickcart quickcart:latest
```

### Test the Application

```bash
curl http://localhost:8081/api/products
```

## ☁️ Azure Cloud Deployment

The application is deployed using Microsoft Azure.

### Azure Resources

| Resource | Configuration |
|---|---|
| Resource Group | quickcart-rg |
| Region | Central India |
| Container Registry | quickcartacr912 |
| Kubernetes Cluster | quickcart-aks |
| Kubernetes Deployment | quickcart |
| Application Replicas | 1 |
| Service Type | LoadBalancer |
| Application Port | 8081 |

### Azure Container Registry

The validated Docker image is stored in Azure Container Registry.

**Image:**

```text
quickcartacr912.azurecr.io/quickcart:latest
```

Jenkins authenticates with Azure using a Service Principal and pushes the container image to ACR.

### Azure Kubernetes Service

The application is deployed to AKS using Kubernetes manifests.

**Deployment:**

```bash
kubectl apply -f deployment.yml
```

**Service:**

```bash
kubectl apply -f service.yml
```

### Verify Deployment

```bash
kubectl get deployments
kubectl get pods
kubectl get services
```

The application was successfully deployed with its container in the Running state and exposed using a Kubernetes LoadBalancer.

## 🌐 Application Access

QuickCart was verified through the public Azure LoadBalancer endpoint.

**Product API:**

```text
http://4.247.239.65:8081/api/products
```

**Note:** The public endpoint is available only while the corresponding Azure infrastructure and application are running. The IP address may change if resources are recreated.

## 🧩 Challenges and Solutions

### 1. Docker Image Vulnerabilities

**Challenge:** Trivy identified vulnerabilities in application dependencies.

**Solution:** Updated affected Tomcat and Jackson dependencies, rebuilt the Docker image, and repeated security scanning until the HIGH and CRITICAL findings were resolved.

### 2. SonarQube Integration

**Challenge:** Integrating automated code analysis and Quality Gate verification into Jenkins.

**Solution:** Configured the SonarQube Jenkins integration and Quality Gate checks to prevent the pipeline from proceeding when the configured quality requirements are not met.

### 3. Azure Authentication

**Challenge:** Allowing Jenkins to securely authenticate with Azure resources.

**Solution:** Created an Azure Service Principal, configured Jenkins credentials, and assigned the required Azure roles.

### 4. Azure Container Registry Integration

**Challenge:** Migrating the image delivery workflow from DockerHub to Azure Container Registry.

**Solution:** Updated the Jenkins pipeline and Kubernetes Deployment to use the ACR image repository.

### 5. Kubernetes Deployment

**Challenge:** Deploying and exposing the containerized application on AKS.

**Solution:** Created Kubernetes Deployment and Service manifests and configured a LoadBalancer Service to provide external access.

## 📈 Future Improvements

Planned enhancements include:

- Prometheus and Grafana monitoring
- Infrastructure as Code using Terraform or Bicep
- Kubernetes Horizontal Pod Autoscaling
- HTTPS and custom domain configuration
- Automated deployment rollback
- Centralized logging and alerting
- Improved application health checks

## 🎯 Key Learnings

This project provided practical experience in:

- Designing and implementing CI/CD pipelines
- Integrating automated testing and code quality checks
- Identifying and resolving container security vulnerabilities
- Managing Docker images using Azure Container Registry
- Configuring Azure Service Principal authentication
- Deploying containerized applications to Kubernetes
- Troubleshooting CI/CD and cloud deployment issues
- Verifying applications deployed to cloud infrastructure

## 📌 Project Status

**Core CI/CD and Azure deployment implementation: Completed ✅**

The final Jenkins pipeline completed successfully, and the QuickCart REST API was verified through the Azure Kubernetes LoadBalancer.

Monitoring, Infrastructure as Code, and advanced deployment strategies are planned as future enhancements.

## 👨‍💻 Author

**Abhisheik Nandan**

DevOps & Cloud Engineering Enthusiast

**GitHub:** https://github.com/Abhisheik912

**Project Repository:** https://github.com/Abhisheik912/quickcart

---

⭐ If you found this project useful, consider starring the repository!
