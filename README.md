# ecoliss-business

ecoliss-business

Etapes à suivre pour le lancement du projet : Après le rapatriement du projet, vous devez suivre quelques petites étapes avant de voir votre projet se lancer à merveille :
1. Récupération et paramétrage du projet :
Avant d’y plonger tête baissée, vous devez tout d’abord identifier avec quelle config vous allez travailler. Pour le cas spécifique de ce projet, nous partirons sur deux configurations à savoir mySQL et postgreSQL.
1.1. Configuration de mySQL :
• Lancer wamp server pour les uns ou Xamp pour d’autres • Lancer « PhpMyAdmin » • Créer une base de données nommée « bd_colis_esic » • Importez dans cette base le script « ecolis-business/src/sql/bd_colis_esic.sql » • Vérifiez les paramètres de connexion à la base se trouvant dans le projet « ecolis-business/src/test/resources/application.yml »  o A cette étape, si vous avez tout bien suivi, vous avez donc le fichier « application.yml » dans lequel, vous l’avez déjà remarqué évidemment qu’il y’a déjà des configuration de base dans ce fichier, votre travail consistera à remplacer les données manquante du fichier par celle que l’on vous donnera pendant la séance.
1.2. Configuration de postgreSQL :
• Lancer pgAdmin • Créez une base nommée « bd_colis_esic_postgres » • Importez dans cette base le script « ecolis-business/src/sql/bd_colis_esic_postgres.sql » • Vérifiez les paramètres de connexion à la base se trouvant dans le projet « colis-ws/src/test/resources/application-postgres.yml » (suivre les mêmes consignes que dans la config de mySQL) • Le mot de passe de base de donnée est le même que vous utilisez pour vos bases postgresql Note : Si vous utilisez postgresql, après la création de votre fichier de configuration, le projet continuera de tourner sur le fichier de configuration de mySQL car celui-ci est le fichier par défaut. Si vous voulez le changer, il vous faudra ajouter une annotation suivante sur chaque fichier de test: @ActiveProfiles("postgres") Et à l’inverse, si vous utilisez mySQL, il vous faudra commenter cette annotation pour pouvoir utiliser le fichier de configuration de mySQL.
1. Lancement du projet:
Ce qui nous interesse ici ce sont les tests unitaires, veillez-vous render au repertoire “ecolis-business/src/test/java”. Vous êtes censé avoir deux packages contenant chacun un fichier de test. Pour lancer le test, vous devez :
• Vous situez sur le fichier de test • Faire un clic droit dans le fichier. Vous avez instantanément un onglet qui s’affiche et sur cet onglet, faites un clic (gauche cette fois ci) sur l’option « Run as » et là, si vous avez bien suivi les étapes antérieures et quelque soit votre config(mySQL ou postgreSQL) cela devrait marcher. • Vous faites la même chose avec le second test et c’est validé. 

