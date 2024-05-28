# ecoliss-business

ecoliss-business

Etapes � suivre pour le lancement du projet�: Apr�s le rapatriement du projet, vous devez suivre quelques petites �tapes avant de voir votre projet se lancer � merveille�:
1. R�cup�ration et param�trage du projet�:
Avant d�y plonger t�te baiss�e, vous devez tout d�abord identifier avec quelle config vous allez travailler. Pour le cas sp�cifique de ce projet, nous partirons sur deux configurations � savoir mySQL et postgreSQL.
1.1. Configuration de mySQL�:
� Lancer wamp server pour les uns ou Xamp pour d�autres � Lancer ��PhpMyAdmin�� � Cr�er une base de donn�es nomm�e ��bd_colis_esic�� � Importez dans cette base le script ��ecolis-business/src/sql/bd_colis_esic.sql�� � V�rifiez les param�tres de connexion � la base se trouvant dans le projet ��ecolis-business/src/test/resources/application.yml��� o A cette �tape, si vous avez tout bien suivi, vous avez donc le fichier ��application.yml�� dans lequel, vous l�avez d�j� remarqu� �videmment qu�il y�a d�j� des configuration de base dans ce fichier, votre travail consistera � remplacer les donn�es manquante du fichier par celle que l�on vous donnera pendant la s�ance.
1.2. Configuration de postgreSQL�:
� Lancer pgAdmin � Cr�ez une base nomm�e ��bd_colis_esic_postgres�� � Importez dans cette base le script ��ecolis-business/src/sql/bd_colis_esic_postgres.sql�� � V�rifiez les param�tres de connexion � la base se trouvant dans le projet ��colis-ws/src/test/resources/application-postgres.yml�� (suivre les m�mes consignes que dans la config de mySQL) � Le mot de passe de base de donn�e est le m�me que vous utilisez pour vos bases postgresql Note�: Si vous utilisez postgresql, apr�s la cr�ation de votre fichier de configuration, le projet continuera de tourner sur le fichier de configuration de mySQL car celui-ci est le fichier par d�faut. Si vous voulez le changer, il vous faudra ajouter une annotation suivante sur chaque fichier de test: @ActiveProfiles("postgres") Et � l�inverse, si vous utilisez mySQL, il vous faudra commenter cette annotation pour pouvoir utiliser le fichier de configuration de mySQL.
1. Lancement du projet:
Ce qui nous interesse ici ce sont les tests unitaires, veillez-vous render au repertoire �ecolis-business/src/test/java�. Vous �tes cens� avoir deux packages contenant chacun un fichier de test. Pour lancer le test, vous devez :
� Vous situez sur le fichier de test � Faire un clic droit dans le fichier. Vous avez instantan�ment un onglet qui s�affiche et sur cet onglet, faites un clic (gauche cette fois ci) sur l�option ��Run as�� et l�, si vous avez bien suivi les �tapes ant�rieures et quelque soit votre config(mySQL ou postgreSQL) cela devrait marcher. � Vous faites la m�me chose avec le second test et c�est valid�. 

