pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t quickcartacr912.azurecr.io/quickcart:latest .'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube server') {
                    bat 'mvn sonar:sonar'
                }

                waitForQualityGate abortPipeline: true
            }
        }

        stage('Trivy Scan') {
            steps {
                bat 'trivy image --severity HIGH,CRITICAL --exit-code 1 quickcartacr912.azurecr.io/quickcart:latest'
            }
        }

        
        stage('Azure Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'azure-jenkins-sp',
                        usernameVariable: 'AZURE_CLIENT_ID',
                        passwordVariable: 'AZURE_CLIENT_SECRET'
                    )
                ]) {
                    bat '''
                    az login --service-principal ^
                    --username %AZURE_CLIENT_ID% ^
                    --password %AZURE_CLIENT_SECRET% ^
                    --tenant 7b72c8cc-229a-44db-9efc-2a302e300bdf
                    '''
                }
            }
        }

        stage('ACR Push') {
            steps {
                bat 'az acr login --name quickcartacr912'
                bat 'docker push quickcartacr912.azurecr.io/quickcart:latest'
            }
        }
        
        stage('Deploy to Azure AKS') {
            steps {
                bat '''
                az aks get-credentials ^
                --resource-group quickcart-rg ^
                --name quickcart-aks ^
                --overwrite-existing

                kubectl apply -f deployment.yml
                kubectl apply -f service.yml
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                bat '''
                kubectl get pods
                kubectl get service quickcart
                '''
            }
        }
    }
}