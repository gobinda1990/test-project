pipeline {
    agent any

    environment {
        IMAGE_BACKEND = "backend-auth:latest"
        IMAGE_FRONTEND = "frontend:latest"
        COMPOSE_FILE_APPS = "docker-compose.yml"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/gobinda1990/test-project.git'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    sh """
                    echo "Building backend image..."
                    docker build -t ${IMAGE_BACKEND} ./backend-auth
                    echo "Building frontend image..."
                    docker build -t ${IMAGE_FRONTEND} ./frontend
                    """
                }
            }
        }

        stage('Deploy Apps') {
            steps {
                script {
                    sh """
                    echo "Stopping any running containers..."
                    docker compose -f ${COMPOSE_FILE_APPS} down
                    echo "Starting containers..."
                    docker compose -f ${COMPOSE_FILE_APPS} up -d
                    """
                }
            }
        }  
    }

    post {
        success {
            echo "Deployment successful! Backend and frontend are running."
        }
        failure {
            echo "Deployment failed!"
        }
    } 
}
