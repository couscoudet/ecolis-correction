# ecoliss-business

ecoliss-business

Etapes à suivre pour le lancement du projet : Après le rapatriement du projet, vous devez suivre quelques petites étapes avant de voir votre projet se lancer à merveille :
1. Récupération et paramétrage du projet :
Avant d’y plonger tête baissée, vous devez tout d’abord identifier avec quelle config vous allez travailler. Pour le cas spécifique de ce projet, nous partirons sur trois configurations à savoir mySQL et postgreSQL et les H2Database.
1.1. Configuration de mySQL :
• Lancer wamp server pour les uns ou Xamp pour d’autres • Lancer « PhpMyAdmin » • Créer une base de données nommée « ecolis_db » • Importez dans cette base le script « ecolis-business/src/sql/bd_colis_data_mysql.sql » • Vérifiez les paramètres de connexion à la base se trouvant dans le projet « ecolis-business/src/test/resources/application-mysql.yml »  o A cette étape, si vous avez tout bien suivi, vous avez donc le fichier « application-mysql.yml » dans lequel, vous l’aurez déjà remarqué évidemment qu’il y’a déjà des configurations de base dans ce fichier, votre travail consistera à remplacer les données manquante du fichier par celle que l’on vous donnera pendant la séance. Rassurez vous aussi que le fichier pom.xml a bien été mise à jour avec les dépendances de mysql.
1.2. Configuration de postgreSQL :
• Lancer pgAdmin • Créez une base nommée « ecolis-db » • Importez dans cette base le script « ecolis-business/src/sql/bd_colis_data_postgres.sql » • Vérifiez les paramètres de connexion à la base se trouvant dans le projet « ecolis-business/src/test/resources/application-postgresql.yml » (suivre les mêmes consignes que dans la config de mySQL) • Le mot de passe de base de donnée est le même que vous utilisez pour vos bases postgresql. Rassurez vous aussi que le fichier pom.xml a bien été mise à jour avec les dépendances de postgresql.
1.3 Configuration de H2Database :
Sur cette partie, il vous faut, comme précédemment, configurer votre fichier de config. Mais la difficulté ici reside dans le fait qu’il n’y a pas de phase d’importation de script. Vérifiez les paramètres de connexion à la base se trouvant dans le projet « ecolis-business/src/test/resources/application-h2.yml » (suivre les mêmes consignes que dans la config de mySQL et de postgreSQL). Rassurez vous aussi que le fichier pom.xml a bien été mise à jour avec les dépendances de H2Database.

Note : Si vous voulez utiliser un fichier de configuration spécifique, il vous faudra ajouter l’annotation suivante sur chaque fichier de test: @ActiveProfiles("postgres"), @ActiveProfiles("mysql") ou @ActiveProfiles("h2") selon la config que vous utilisez.

1. Lancement du projet:
Ce qui nous interesse ici ce sont les tests unitaires, veillez-vous rendre au repertoire “ecolis-business/src/test/java”. Vous êtes censé avoir deux packages contenant chacun un fichier de test. Pour lancer le test, vous devez :
• Vous situez sur le fichier de test • Faire un clic droit dans le fichier. Vous avez instantanément un onglet qui s’affiche et sur cet onglet, faites un clic (gauche cette fois ci) sur l’option « Run as » et là, si vous avez bien suivi les étapes antérieures et quelque soit votre config(mySQL ou postgreSQL) cela devrait marcher. • Vous faites la même chose avec le second test et c’est validé. 

Note : petit point sur la partie H2Database. Je vous le disais plus haut, avec les h2Database, le fonctionnement est légèrement différent. Déjà lorsque vous lancerez les tests, certains ne marcheront pas et tout simplement parce que vos tables sont vides. N’ayant pas de script prêt à l’emploi, tout se jouera sur la partie web service du projet. Mais pour ce faire, vous devez tout d’abord commenter un bloc de code se situant dans le package « config » plus précisément dans la classe « EcolisDbConfig ». Vous devez vous rendre dans cette classe donc et commenter les méthodes « LocalContainerEntityManagerFactoryBean» et « PlatformTransactionManager ».


