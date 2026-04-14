pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Karthik-hr18/MyMavenFireFox.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Application') {
            steps {
                sh '''
                export MOZ_HEADLESS=1
                export DISPLAY=:99
                mvn exec:java -Dexec.mainClass="com.example.App"
                '''
            }
        }
    }

    post {
        success {
            echo 'Build and execution successful! ✅'
        }
        failure {
            echo 'Build failed! ❌'
        }
    }
}
