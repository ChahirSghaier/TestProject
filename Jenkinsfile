pipeline {
    agent any

    stages {
        stage('Récupération du code source') {
            steps {
             // Ceci récupère le code source depuis le référentiel Git
                checkout scm     }
        }
        stage('Affichage de la date système') {
            steps {
                script {
                    def currentDate = new Date().format("yyyy-MM-dd HH:mm:ss")
                    echo "Date système : ${currentDate}"
                }
            }
        }
    }
}
