#!groovy
node {
    def mavenHome="${tool 'M3'}"
    checkout scm

    stage('Build') {
        sh "${mavenHome}/bin/mvn clean test"
    }

    stage('Version') {
        sh "${mavenHome}/bin/mvn versions:set -DnewVersion=1.0.${BUILD_NUMBER}"
    }

    stage('Publish') {
        sh "${mavenHome}/bin/mvn -X -s settings.xml clean package -DskipTests docker:build -DpushImageTag"
    }
}