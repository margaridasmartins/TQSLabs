pipeline {
    agent any
    tools {
        jdk 'jdk-11.0.4'
        maven 'maven 3.6.3'
    }
    stages {
        stage('change dir'){
            dir('lab4/P2CarManager/"')
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
        stage('Install') {
            steps {
                sh "mvn clean install"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                }
            }
        }
    }
}
