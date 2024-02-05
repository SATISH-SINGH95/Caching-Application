pipeline{
    agent any
    tools{
        maven 'MAVEN_HOME'
    }
    stages{
        stage("mvn clean install"){
            steps{
                bat 'mvn clean install'
            }
        }
        stage("deploy to container"){
            steps{
                 deploy adapters: [tomcat9(credentialsId: '84e56306-0e3d-4847-98b0-c515a2a58672', path: '', url: 'http://localhost:8081/')], contextPath: 'Caching', war: '**/*.war'
            }
        }
    }
    post{
        always{
            echo 'Always message always be displayed'
        }
        success{
            echo 'This message will be displayed after successful execution of each stages'
        }
        failure{
            echo 'This message will be displayed if any of stages failed to execute'
        }
    }
}
