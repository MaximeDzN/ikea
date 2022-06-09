pipeline {
	agent any
    tools {
    	maven 'maven'
        jdk 'java-11'
    }

	stages {
		stage('Build') {
			steps {
				echo 'Building..'
				sh("mvn clean package")
			}
			post {
                success {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
		}
		stage('Test') {
			steps {
				echo 'Testing..'
				sh("mvn test")
			}
		}
	}
}