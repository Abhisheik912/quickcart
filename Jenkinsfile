pipeline{
    agent any

    stages{
        stage('Build'){
            steps{
               bat 'mvn clean package'
            }       
        }

        stage('Test'){
            steps{
                bat 'mvn test'
            }
        }

        stage('Docker Build'){
            steps{
               bat 'docker build -t abhisheik912/quickcart:latest .'
            }       
        }


        stage('SonarQube'){
            steps{
                withSonarQubeEnv ('sonarqube server')
                    {
                        bat 'mvn sonar:sonar'
                    }
                waitForQualityGate abortPipeline: true
            }
        }
        stage('Trivy Scan'){
            steps{
                bat 'trivy image --severity HIGH,CRITICAL --exit-code 1 abhisheik912/quickcart:latest'
            }
        }
        stage('DockerHUb'){
            steps{
                bat 'docker push abhisheik912/quickcart:latest'
            }
        }
        stage('Deploy to Kubernetes'){
            steps{
                bat 'kubectl apply -f deployment.yml'
            }
        }
            
    }
}    