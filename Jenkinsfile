pipeline{
    agent any

    environment {
        PROJECT_NAME = "bariq123456-dev"
        BUILD_NAME = "java-bni-project-git"
    }

    stages {
        stage('Trigger Build in OpenShift'){
            steps {
                sh "oc start-build ${BUILD_NAME} --from-dir=. --follow -n ${PROJECT_NAME}"
            }
        }

        stage ('Deploy to OpenShift') {
            steps {
                sh "oc rollout restart deployment/${BUILD_NAME} -n ${PROJECT_NAME}"
            }
        }
    }

    post (
        success {
            echo 'Build & Deployment Successfull via OpenShift BuildConfig!'
        }
        failure {
            echo 'Gagal menjalankan pipeline'
        }
    )
}