pipeline {
    agent any

    environment {
        IMAGE_BACKEND = "backend-auth:latest"
        IMAGE_FRONTEND = "frontend:latest"
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
                    docker build -t ${IMAGE_BACKEND} ./backend-auth
                    docker build -t ${IMAGE_FRONTEND} ./frontend
                    """
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                docker compose -f docker-compose.yml down
                docker compose -f docker-compose.yml up -d --build
                """
            }
        }

        stage('Health Check') {
            steps {
                script {
                    sh '''
                    echo "Waiting for backend to start..."
                    for i in {1..20}; do
                        if curl -s -f http://10.153.43.8:8083/api/actuator/health; then
                            echo "Backend is up!"
                            break
                        fi
                        echo "Waiting..."
                        sleep 5
                    done
                    curl -f http://10.153.43.8:8083/api/auth/login || (echo "Health check failed!" && exit 1)
                    '''
                }
            }
        }
    }
}
