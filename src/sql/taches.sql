-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 30 juin 2019 à 19:34
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `taches`
--

-- --------------------------------------------------------

--
-- Structure de la table `directeur`
--

DROP TABLE IF EXISTS `directeur`;
CREATE TABLE IF NOT EXISTS `directeur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `nom`) VALUES
(49, 'ZOME'),
(50, 'Sarkozy'),
(51, 'ZOME'),
(52, 'Sarkozy'),
(53, 'ZOME'),
(54, 'Sarkozy');

-- --------------------------------------------------------

--
-- Structure de la table `employe2`
--

DROP TABLE IF EXISTS `employe2`;
CREATE TABLE IF NOT EXISTS `employe2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employe2`
--

INSERT INTO `employe2` (`id`, `nom`) VALUES
(49, 'ZOME'),
(50, 'Sarkozy'),
(51, 'ZOME'),
(52, 'Sarkozy'),
(53, 'ZOME'),
(54, 'Sarkozy');

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `ID_TACHE` int(4) NOT NULL AUTO_INCREMENT,
  `ID_USER` int(4) NOT NULL,
  `LIBELLE` varchar(100) NOT NULL,
  `STATUT` int(1) NOT NULL,
  `TEMPS_RESTANT` int(4) NOT NULL,
  `TEMPS_IMPARTI` int(4) NOT NULL,
  `DATE_CREATION` timestamp NOT NULL,
  PRIMARY KEY (`ID_TACHE`),
  KEY `FK_USER` (`ID_USER`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`ID_TACHE`, `ID_USER`, `LIBELLE`, `STATUT`, `TEMPS_RESTANT`, `TEMPS_IMPARTI`, `DATE_CREATION`) VALUES
(5, 1, 'Envoi d\'email', 1, 30, 20, '2017-01-15 14:33:57'),
(6, 1, 'Modele conceptuel De donnees', 2, 20, 27, '2017-01-15 14:41:36'),
(7, 2, 'Diagramme de classes', 1, 10, 10, '2017-01-15 14:41:57'),
(9, 4, 'Transfert de docs FTP', 1, 30, 30, '2017-01-19 09:42:57');

-- --------------------------------------------------------

--
-- Structure de la table `taliris_log`
--

DROP TABLE IF EXISTS `taliris_log`;
CREATE TABLE IF NOT EXISTS `taliris_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Log_Id correspond à la clé primaire de cette table. elle est auto increment',
  `START_DATE` timestamp NOT NULL COMMENT 'Start_Date correspond à la date de debut de l''execution d''une tâche vers n''importe quel table taliris',
  `TABLE_NAME` varchar(100) NOT NULL COMMENT 'Table_Name correspond au Nom de la Table Taliris au quel la tâche est effectuée',
  `INSERTED_ROW` int(11) NOT NULL COMMENT 'Inserted_Row correspond au nombre de ligne inserée dans la table taliris',
  `UPDATE_ROW` int(11) NOT NULL COMMENT 'Update_Row correspond au nombre de ligne mise à jour dans la table taliris',
  `END_DATE` timestamp NOT NULL COMMENT 'End_Date correspond à la date de fin de la tâche',
  `MESSAGE_ERROR` varchar(3000) NOT NULL COMMENT 'Message_Error correspond au message d''erreur si il y en aura',
  `STATUS` varchar(2) NOT NULL COMMENT 'Status correspond au status de déroulement de la tâche',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=latin1 COMMENT='La table Taliris_Log est la table qui trace toutes actions dans nos table Taliris';

--
-- Déchargement des données de la table `taliris_log`
--

INSERT INTO `taliris_log` (`LOG_ID`, `START_DATE`, `TABLE_NAME`, `INSERTED_ROW`, `UPDATE_ROW`, `END_DATE`, `MESSAGE_ERROR`, `STATUS`) VALUES
(201, '2019-01-17 04:38:36', 'EMPLOYE', 2, 0, '2019-01-17 04:38:36', 'NO MESSAGE', 'OK'),
(202, '2019-01-17 04:54:36', 'EMPLOYE', 2, 0, '2019-01-17 04:54:36', 'NO MESSAGE', 'OK'),
(203, '2019-01-17 14:49:02', 'EMPLOYE', 2, 0, '2019-01-17 14:49:02', 'NO MESSAGE', 'OK');

-- --------------------------------------------------------

--
-- Structure de la table `test_param`
--

DROP TABLE IF EXISTS `test_param`;
CREATE TABLE IF NOT EXISTS `test_param` (
  `Code` varchar(10) NOT NULL,
  `valeur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `test_param`
--

INSERT INTO `test_param` (`Code`, `valeur`) VALUES
('CODE_OSMOS', '26602'),
('ERP_SOURCE', 'SAGE_CAMEROUN');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID_USER` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(25) NOT NULL,
  `PRENOM` varchar(25) NOT NULL,
  `SEXE` int(1) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  `CODE_POSTAL` int(5) NOT NULL,
  `CODE_DEPARTEMENT` int(1) NOT NULL,
  `DATE_INSCRIPTION` timestamp NOT NULL,
  PRIMARY KEY (`ID_USER`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID_USER`, `NOM`, `PRENOM`, `SEXE`, `EMAIL`, `PASSWORD`, `CODE_POSTAL`, `CODE_DEPARTEMENT`, `DATE_INSCRIPTION`) VALUES
(1, 'ZOME', 'Christophe', 1, 'christophe.zome@sogeti.com', 'pwd_esic', 35700, 0, '2017-01-09 17:34:35'),
(3, 'LE PEN', 'Marine', 2, 'marine.lepen@fn.fr', 'm.lepen', 82003, 3, '2017-01-10 06:39:48'),
(4, 'PEILLON', 'Vincent', 1, 'vincent.peillon@ps.fr', 'vpei', 72000, 0, '2017-01-16 05:27:07'),
(5, 'ROYAL', 'Segolene', 2, 'segolene.royal@ps.fr', 'sego', 72004, 72004, '2017-01-16 05:28:03'),
(19, 'MACRON', 'Brigitte', 2, 'brigitte.macron@gouv.fr', 'pwd_bri', 75107, 3, '2017-08-25 04:45:52'),
(9, 'Aubry', 'Martine', 2, 'martine.aubry@ps.fr', 'aub', 81016, 0, '2017-01-17 04:40:38'),
(11, 'Alain', 'JUPPE', 1, 'alain.juppe@gouv.fr', 'der', 23145, 0, '2017-01-20 11:05:22'),
(12, 'Arnaud', 'MONTEBOURG', 1, 'arnaud.montbourg@s.fr', 'mont', 42008, 42008, '2017-01-23 14:02:51'),
(20, 'RAUX', 'Emmanuelle', 2, 'emma.raux@edadvisory.com', 'raux', 72108, 72108, '2018-05-28 03:19:58');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
