pipeline {
    agent any
    tools {
        jdk 'jdk-11.0.4'
        maven 'maven 3.6.3'
    }
    stages {
        stage('change directory'){
            steps{
                dir('lab4/P2CarManager'){
                    sh "$PWD"
                }
            }
        }
        stage('test java installation') {
            steps {
                sh 'java -version'
            }
        }
        stage('test maven installation') {
            steps {
                sh 'mvn -version'
            }
        }
    }
}
