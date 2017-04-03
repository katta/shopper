#!groovy
node {
    def mvn="${tool 'M3'}/bin/mvn"
    checkout scm

    stage('Build') {
        sh "${mvn} clean test"
    }

    stage('Version') {
        sh "${mvn} versions:set -DnewVersion=1.0.${BUILD_NUMBER}"
    }

    stage('Publish') {
        sh "${mvn} clean install -DskipTests fabric8:build fabric8:push fabric8:resource"
    }

    stage('Deploy') {
        sh "kubectl apply -f activemq/kubernetes.yml"
        sh "kubectl apply -f mongodb/kubernetes.yml"
        sh "kubectl apply -f customers/target/classes/META-INF/fabric8/kubernetes.yml"
        sh "kubectl apply -f orders/target/classes/META-INF/fabric8/kubernetes.yml"
    }
}