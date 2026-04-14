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
                # Kill any existing Xvfb (avoid conflicts)
                pkill Xvfb || true

                # Start fresh virtual display
                Xvfb :99 -screen 0 1024x768x24 > /dev/null 2>&1 &
                
                # Wait for it to stabilize
                sleep 5

                # Export environment
                export DISPLAY=:99
                export MOZ_HEADLESS=1

                # Debug (optional)
                echo "DISPLAY=$DISPLAY"

                # Run application
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
