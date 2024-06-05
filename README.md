# ecoliss-business

ecoliss-business

Etapes � suivre pour le lancement du projet�: Apr�s le rapatriement du projet, vous devez suivre quelques petites �tapes avant de voir votre projet se lancer � merveille�:
1. R�cup�ration et param�trage du projet�:
Avant d�y plonger t�te baiss�e, vous devez tout d�abord identifier avec quelle config vous allez travailler. Pour le cas sp�cifique de ce projet, nous partirons sur trois configurations � savoir mySQL et postgreSQL et les H2Database.
1.1. Configuration de mySQL�:
� Lancer wamp server pour les uns ou Xamp pour d�autres � Lancer ��PhpMyAdmin�� � Cr�er une base de donn�es nomm�e ��ecolis_db�� � Importez dans cette base le script ��ecolis-business/src/sql/bd_colis_data_mysql.sql�� � V�rifiez les param�tres de connexion � la base se trouvant dans le projet ��ecolis-business/src/test/resources/application-mysql.yml��� o A cette �tape, si vous avez tout bien suivi, vous avez donc le fichier ��application-mysql.yml�� dans lequel, vous l�aurez d�j� remarqu� �videmment qu�il y�a d�j� des configurations de base dans ce fichier, votre travail consistera � remplacer les donn�es manquante du fichier par celle que l�on vous donnera pendant la s�ance. Rassurez vous aussi que le fichier pom.xml a bien �t� mise � jour avec les d�pendances de mysql.
1.2. Configuration de postgreSQL�:
� Lancer pgAdmin � Cr�ez une base nomm�e ��ecolis-db�� � Importez dans cette base le script ��ecolis-business/src/sql/bd_colis_data_postgres.sql�� � V�rifiez les param�tres de connexion � la base se trouvant dans le projet ��ecolis-business/src/test/resources/application-postgresql.yml�� (suivre les m�mes consignes que dans la config de mySQL) � Le mot de passe de base de donn�e est le m�me que vous utilisez pour vos bases postgresql. Rassurez vous aussi que le fichier pom.xml a bien �t� mise � jour avec les d�pendances de postgresql.
1.3 Configuration de H2Database�:
Sur cette partie, il vous faut, comme pr�c�demment, configurer votre fichier de config. Mais la difficult� ici reside dans le fait qu�il n�y a pas de phase d�importation de script. V�rifiez les param�tres de connexion � la base se trouvant dans le projet ��ecolis-business/src/test/resources/application-h2.yml�� (suivre les m�mes consignes que dans la config de mySQL et de postgreSQL). Rassurez vous aussi que le fichier pom.xml a bien �t� mise � jour avec les d�pendances de H2Database.

Note�: Si vous voulez utiliser un fichier de configuration sp�cifique, il vous faudra ajouter l�annotation suivante sur chaque fichier de test: @ActiveProfiles("postgres"), @ActiveProfiles("mysql") ou @ActiveProfiles("h2") selon la config que vous utilisez.

1. Lancement du projet:
Ce qui nous interesse ici ce sont les tests unitaires, veillez-vous rendre au repertoire �ecolis-business/src/test/java�. Vous �tes cens� avoir deux packages contenant chacun un fichier de test. Pour lancer le test, vous devez :
� Vous situez sur le fichier de test � Faire un clic droit dans le fichier. Vous avez instantan�ment un onglet qui s�affiche et sur cet onglet, faites un clic (gauche cette fois ci) sur l�option ��Run as�� et l�, si vous avez bien suivi les �tapes ant�rieures et quelque soit votre config(mySQL ou postgreSQL) cela devrait marcher. � Vous faites la m�me chose avec le second test et c�est valid�. 

Note�: petit point sur la partie H2Database. Je vous le disais plus haut, avec les h2Database, le fonctionnement est l�g�rement diff�rent. D�j� lorsque vous lancerez les tests, certains ne marcheront pas et tout simplement parce que vos tables sont vides. N�ayant pas de script pr�t � l�emploi, tout se jouera sur la partie web service du projet. Mais pour ce faire, vous devez tout d�abord commenter un bloc de code se situant dans le package ��config�� plus pr�cis�ment dans la classe ��EcolisDbConfig��. Vous devez vous rendre dans cette classe donc et commenter les m�thodes ��LocalContainerEntityManagerFactoryBean� et ��PlatformTransactionManager��.


