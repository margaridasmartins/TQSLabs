pipeline {
    agent any
    tools {
        jdk 'jdk-11.0.4'
        maven 'maven 3.6.3'
    }
    stages {
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
        stage('Install') {
            steps {
                dir('lab4/P2CarManager/'){
                    sh "$PWD"
                    sh "sudo mvn clean install"
                }
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                }
            }
        }
    }
}
