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
                    docker compose -f ${COMPOSE_FILE_APPS} up -d --build
                    """
                }
            }
        }

        stage('Health Check Backend') {
            steps {
                script {
                    sh '''
                    echo "Waiting for backend to start..."
                    for i in {1..20}; do
                        if curl -s -f http://localhost:8083/api/actuator/health; then
                            echo "Backend is up!"
                            break
                        fi
                        echo "Waiting..."
                        sleep 5
                    done
                    curl -f http://localhost:8083/api/auth/login || (echo "Backend health check failed!" && exit 1)
                    '''
                }
            }
        }

        stage('Health Check Frontend') {
            steps {
                script {
                    sh '''
                    echo "Checking frontend..."
                    for i in {1..20}; do
                        if curl -s -f http://localhost:8084; then
                            echo "Frontend is up!"
                            break
                        fi
                        echo "Waiting..."
                        sleep 5
                    done
                    curl -f http://localhost:8084 || (echo "Frontend health check failed!" && exit 1)
                    '''
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
