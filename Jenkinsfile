node {
  checkout scm
  stage('Build') {
    withMaven {
      sh 'mvn clean compile'
    }

  }

  stage('Publish') {
    echo 'Placeholder to publish docker images'
  }
}