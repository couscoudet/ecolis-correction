-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Dim 17 Avril 2016 à 21:03
-- Version du serveur :  5.5.38
-- Version de PHP :  5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `bd_colis_esic`
--

-- --------------------------------------------------------

--
-- Structure de la table `Alerte`
--

CREATE TABLE `Alerte` (
`id_alerte` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `ville_depart` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ville_arrivee` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_depot` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Date de depot de l''alerte'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Alerte`
--

INSERT INTO `Alerte` (`id_alerte`, `id_utilisateur`, `ville_depart`, `ville_arrivee`, `date_depot`) VALUES
(1, NULL, 'Bordeaux, France', 'Nantes, France', '2016-04-11 16:15:05'),
(2, 1, 'YaoundÃ©, Centre, Cameroun', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(3, 3, 'YaoundÃ©, Centre, Cameroun|NEW|NEW|NEW|NEW|NEW|NEW|NEW', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(4, 3, 'YaoundÃ©, Centre, Cameroun', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(7, 5, 'Ndjamena, Tchad', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(8, 3, 'Dakar, SÃ©nÃ©gal', 'Abidjan, Lagunes, CÃ´te d''Ivoire', '2016-04-10 08:30:49'),
(9, 2, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(10, 6, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(11, 1, 'Paris;France', 'Bamako,Mali', '2016-04-10 08:30:49'),
(16, 1, 'MontMagny', 'Nanterre', '2016-04-10 08:30:49'),
(17, 6, 'MontMagny', 'Nanterre', '2016-04-10 08:30:49'),
(18, 6, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(19, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:38:33'),
(20, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:38:47'),
(21, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:39:27'),
(25, 1, 'Montauban,France', 'Orleans,France', '2016-04-13 06:31:28'),
(27, 1, 'Montauban,France', 'Orleans,France', '2016-04-14 06:35:06'),
(28, 1, 'Montauban,France', 'Orleans,France', '2016-04-14 06:36:15'),
(29, 1, 'Mbouda-Cameroun', 'Nkongsamba-Cameroun', '2016-04-15 06:08:21');

-- --------------------------------------------------------

--
-- Structure de la table `Annonce`
--

CREATE TABLE `Annonce` (
`id_annonce` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `nature_colis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poids` decimal(10,0) NOT NULL,
  `unite_poids` int(11) NOT NULL,
  `date_depot` datetime NOT NULL,
  `date_depart` datetime NOT NULL,
  `date_arrivee` datetime NOT NULL,
  `ville_depart` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ville_arrivee` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prime` decimal(10,0) NOT NULL,
  `devise` int(11) NOT NULL,
  `statut` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type_annonce` int(11) NOT NULL DEFAULT '1',
  `detail` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Annonce`
--

INSERT INTO `Annonce` (`id_annonce`, `id_utilisateur`, `nature_colis`, `poids`, `unite_poids`, `date_depot`, `date_depart`, `date_arrivee`, `ville_depart`, `ville_arrivee`, `prime`, `devise`, `statut`, `type_annonce`, `detail`) VALUES
(1, 1, '1', 50, 1, '2015-06-30 16:44:17', '2015-06-08 00:00:00', '2015-06-15 00:00:00', 'Nantes, France', 'Paris, France', 50, 1, '1', 2, 'loool'),
(2, 3, '2', 34, 2, '2015-07-01 15:14:41', '2015-07-16 00:00:00', '2015-07-26 00:00:00', 'Paris, France', 'Bamako, Mali', 456, 2, '1', 2, 'Merci de me contacter pour plus de détails'),
(3, 6, '2', 12, 1, '2015-07-29 15:38:54', '2015-05-12 00:00:00', '2015-04-10 00:00:00', 'Paris, France', 'Douala, Littoral, Cameroun', 45, 1, '1', 2, 'Je propose de vous le ramener'),
(4, 6, 'Chaussures', 2, 0, '2016-04-15 15:59:02', '2016-04-01 00:00:00', '2017-09-01 00:00:00', 'Louanda, Angola', 'Montreal,Canada', 0, 1, '1', 1, 'J''aimerai envoyer des chaussures à ma cousine residant au Canada.');

-- --------------------------------------------------------

--
-- Structure de la table `Comment`
--

CREATE TABLE `Comment` (
`id_comment` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `corps` text COLLATE utf8_unicode_ci,
  `date_creation` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Comment`
--

INSERT INTO `Comment` (`id_comment`, `id_utilisateur`, `corps`, `date_creation`) VALUES
(2, 1, 'Passager ne prevenant pas du retard', '2015-07-04 13:05:39'),
(3, 1, 'Le colis est arrivé nickel, merci beaucoup', '2015-07-04 13:06:10'),
(4, 1, 'N''est pas très communicant', '2015-07-04 13:06:34'),
(5, 6, 'N''a jamais répondu à mes messages', '2015-07-29 15:23:22'),
(6, 2, 'Transporteur tres ponctuel, je le recommande pour vos colis', '2016-01-12 23:04:45'),
(7, 2, 'Transporteur semi ponctuel', '2016-01-12 23:05:16'),
(8, 2, 'Transporteur tres ponctuel', '2016-04-11 17:32:32'),
(9, 2, 'Transporteur tres ponctuel', '2016-04-11 17:35:00'),
(10, 2, 'Transporteur tres ponctuel', '2016-04-11 17:37:39'),
(11, 2, 'Transporteur tres ponctuel', '2016-04-11 17:38:47'),
(12, 2, 'Transporteur tres ponctuel', '2016-04-11 17:39:27');

-- --------------------------------------------------------

--
-- Structure de la table `Image`
--

CREATE TABLE `Image` (
`id_image` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `chemin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Image`
--

INSERT INTO `Image` (`id_image`, `id_utilisateur`, `chemin`, `date_creation`) VALUES
(3, 1, 'b90f4e93674e6709a40740f4e4f9a8d3e7e3931f.jpeg', '2015-10-06 10:14:33'),
(4, 6, 'f787250fe425379056128efd746af51c2a8021d2.jpeg', '2015-12-10 10:14:33');

-- --------------------------------------------------------

--
-- Structure de la table `Message`
--

CREATE TABLE `Message` (
`id_message` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `id_annonce` int(11) NOT NULL,
  `corps` text COLLATE utf8_unicode_ci,
  `date_creation` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Message`
--

INSERT INTO `Message` (`id_message`, `id_utilisateur`, `id_annonce`, `corps`, `date_creation`) VALUES
(1, 1, 1, 'dadadafafff', '2015-07-04 16:47:49'),
(2, 6, 2, 'dhfhdhdhfdhd', '2015-07-05 08:33:09');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
`id_utilisateur` int(11) NOT NULL,
  `civilite` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `derniere_connexion` datetime DEFAULT NULL,
  `annee_de_naissance` int(11) NOT NULL,
  `telephone` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Date d''inscription au syst?me'
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id_utilisateur`, `civilite`, `name`, `login`, `password`, `email`, `enabled`, `derniere_connexion`, `annee_de_naissance`, `telephone`, `date_inscription`) VALUES
(1, '1', 'Mbougueung', 'amb', 'amb', 'achille@achille.com', 1, '2015-07-04 12:17:56', 1966, '0578678990', '2016-04-15 14:08:25'),
(2, '1', 'Le Guen', 'ppa', 'lgu', 'paule@paule.com', 1, '2015-07-04 09:03:23', 2015, '0761705745', '2016-04-15 14:08:42'),
(3, '2', 'Le Maire', 'jje', 'lma', 'jean@jean.com', 1, '2015-07-01 12:54:18', 1994, '0978678990', '2016-04-15 14:08:56'),
(5, '2', 'Marteau', 'mmo', 'mpo', 'moi@moi.com', 1, '2015-07-04 09:47:30', 1992, '0676564389', '2016-04-15 14:09:08'),
(6, '1', 'ZOME', 'czome', 'czome', 'christophe.zome@indyli-services.com', 1, '2015-07-29 15:57:41', 2000, '0624623647', '2016-04-15 14:00:20'),
(39, '2', 'Farmer', 'far', 'tito', 'mylene@solutec.com', 1, NULL, 1990, '0635247895', '2016-04-15 14:09:15');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Alerte`
--
ALTER TABLE `Alerte`
 ADD PRIMARY KEY (`id_alerte`), ADD KEY `FK_USER_DEPOSE_ALERTE` (`id_utilisateur`);

--
-- Index pour la table `Annonce`
--
ALTER TABLE `Annonce`
 ADD PRIMARY KEY (`id_annonce`), ADD KEY `FK_USER_DEPOSE_ANNONCE` (`id_utilisateur`);

--
-- Index pour la table `Comment`
--
ALTER TABLE `Comment`
 ADD PRIMARY KEY (`id_comment`), ADD KEY `FK_USER_DEPOSE_COMMENT` (`id_utilisateur`);

--
-- Index pour la table `Image`
--
ALTER TABLE `Image`
 ADD PRIMARY KEY (`id_image`), ADD KEY `FK_USER_DEPOSE_IMAGE` (`id_utilisateur`);

--
-- Index pour la table `Message`
--
ALTER TABLE `Message`
 ADD PRIMARY KEY (`id_message`), ADD KEY `FK_ANNONCE_POSE_MESSAGE` (`id_annonce`), ADD KEY `FK_USER_DEPOSE_MESSAGE` (`id_utilisateur`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
 ADD PRIMARY KEY (`id_utilisateur`), ADD UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email`), ADD KEY `UNIQ_8D93D64992FC23A8` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Alerte`
--
ALTER TABLE `Alerte`
MODIFY `id_alerte` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT pour la table `Annonce`
--
ALTER TABLE `Annonce`
MODIFY `id_annonce` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `Comment`
--
ALTER TABLE `Comment`
MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `Image`
--
ALTER TABLE `Image`
MODIFY `id_image` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `Message`
--
ALTER TABLE `Message`
MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Alerte`
--
ALTER TABLE `Alerte`
ADD CONSTRAINT `FK_USER_DEPOSE_ALERTE` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `Annonce`
--
ALTER TABLE `Annonce`
ADD CONSTRAINT `FK_USER_DEPOSE_ANNONCE` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `Comment`
--
ALTER TABLE `Comment`
ADD CONSTRAINT `FK_USER_DEPOSE_COMMENT` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `Image`
--
ALTER TABLE `Image`
ADD CONSTRAINT `FK_USER_DEPOSE_IMAGE` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `Message`
--
ALTER TABLE `Message`
ADD CONSTRAINT `FK_ANNONCE_POSE_MESSAGE` FOREIGN KEY (`id_annonce`) REFERENCES `Annonce` (`id_annonce`) ON DELETE CASCADE,
ADD CONSTRAINT `FK_USER_DEPOSE_MESSAGE` FOREIGN KEY (`id_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`) ON DELETE CASCADE;
