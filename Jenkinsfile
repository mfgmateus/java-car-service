@Library('projetas-library')

pipeline {
  agent {
    node {
    
    }
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
        junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true)
      }
    }
  }
}
