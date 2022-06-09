pipeline {
    agent any
    
     environment {
        String branchName = "master"
        // https://github.com/Flav1-ann/MyRestoSpring
        String repoUrl = "https://github.com/MaximeDzN/ikea.git"
        registry = "flav1ann/ikea"
        registryCredential = 'dockerhub'
        dockerImage = ''
        
        
        AWS_ACCESS_KEY_ID     = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        
    }
    /*
    triggers {
        pollSCM '* * * * *'
    }*/
    stages {
        stage('Checkout') {
            steps {
                script {
                    sh("pwd")
                    sh("ls -ahl")
                    echo 'Cloning files from (branch: "' + branchName + '" )'
                    echo 'Start cloning the github repository from (branch: "' + branchName + ' ") .....'
                    // The below will clone your repo and will be checked out to master branch by default.
                    git branch: branchName, url: repoUrl
                    sh("ls -ahl")
                    echo 'repository clone on branch master done.'
                }
            }
            }
        stage('Compile') {
              agent {
                docker { 
                    image 'maven:3.8.4-openjdk-11-slim' 
                    reuseNode true
                }
            }
            steps {
                script {
                    echo 'starting spring boot application compilation .....'
                    sh("mvn compile")
                    echo 'spring boot application compilation doned'
                }
            }
        }
        stage('Test') {
               agent {
                docker { 
                    image 'maven:3.8.4-openjdk-11-slim' 
                    reuseNode true
                }
            }
            steps {
                script {
                    echo 'starting spring boot application unit test .....'
                    sh("mvn test")
                    echo 'spring boot application unit test doned'
                }
            }
        }
        stage('Doc') {
               agent {
                docker { 
                    image 'maven:3.8.4-openjdk-11-slim' 
                    reuseNode true
                }
            }
            steps {
                script {
                    echo 'Generating spring-boot application documentation .....'
                    sh("mvn javadoc:javadoc")
                    echo 'Documentation generated'
                }
            }
        }
        stage('Docker image build') {
                steps {
                    script {
                        echo 'Start building docker image of the application ......'
                        dockerImage = docker.build registry + ":latest"
                        echo 'Docker image build done.'
                    }
                }
            }
        stage('Docker image push on docker hub') {
            steps {
                script {
                    echo 'Pushing the docker image application builded to docker hub.'

                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                    echo 'Docker image build done.'
                }
              
            }
        }
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:latest"
            }
        }
        stage('Terraform init') {
            steps {    
                sh("pwd")
                dir("Terraform/app/") {
                    sh label: '' , script: 'terraform init'
                }
            }
        }
    }
    
    post {
    failure {
            echo 'J\'aime le paté !'
              step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "jon.jolate@gmail.com", sendToIndividuals: true])
    }
}
}
