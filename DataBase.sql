-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 16 avr. 2019 à 20:03
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
-- Base de données :  `bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `follow`
--

DROP TABLE IF EXISTS `follow`;
CREATE TABLE IF NOT EXISTS `follow` (
  `user_id1` int(11) NOT NULL,
  `user_id2` int(11) NOT NULL,
  `follow_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id1`,`user_id2`),
  KEY `check_user2` (`user_id2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `follow`
--

INSERT INTO `follow` (`user_id1`, `user_id2`, `follow_date`) VALUES
(9, 8, '2019-03-25 13:36:12'),
(9, 10, '2019-04-16 01:17:09'),
(18, 9, '2019-04-10 17:07:52'),
(23, 9, '2019-04-10 17:09:14');

-- --------------------------------------------------------

--
-- Structure de la table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
CREATE TABLE IF NOT EXISTS `invitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(4) NOT NULL,
  `id_friend` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_id_user` (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `session_key` varchar(64) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `session_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `session_root` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`session_key`),
  KEY `user_foreign_key` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sessions`
--

INSERT INTO `sessions` (`session_key`, `user_id`, `session_start`, `session_root`) VALUES
('EuW0MQZbX8BmzZZxobvbpATnkadAXUze', 50, '2019-04-16 10:37:47', 0),
('hlyMd2X4fprYQCuvAdNXndohpBKZjTqN', 9, '2019-04-16 17:15:35', 0),
('ltrOaQVWfaAreap2ODlivoOfpilrsHjQ', 8, '2019-04-16 09:35:24', 0),
('PbRWRyzHnqVIzAFDi9xOsm4Lw7SwOK3I', 11, '2019-04-16 09:35:24', 0),
('PnCNBtYHyYsWGbM2HDqIKZAGtAgXtSCf', 23, '2019-04-15 22:53:00', 0),
('Zd0LMvsVTqxGe2QGRz6kpjItQeUmGFDE', 48, '2019-04-15 14:03:03', 0);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(32) NOT NULL,
  `user_password` blob NOT NULL,
  `user_mail` varchar(24) DEFAULT NULL,
  `user_prenom` varchar(16) DEFAULT NULL,
  `user_nom` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login` (`user_login`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `user_login`, `user_password`, `user_mail`, `user_prenom`, `user_nom`) VALUES
(8, 'chikow', 0x6d6470, 'walid@live.fr', 'Laouer', 'Walid'),
(9, 'neel', 0x313233, 'bealref@gmail.com', 'BELAREF', 'Nail'),
(10, 'Delpax', 0x31323334, 'Delpax@gmail.com', 'Pax', 'del'),
(11, 'Delpaxll', 0x31323334, 'Dellpax@gmail.com', 'Pax', 'del'),
(17, 'user2', 0x7573657232, 'User2@gmail.com', 'user2', 'user2'),
(18, 'test', 0x74657374, 'test', 'test@gmail.com', 'test'),
(19, 'user0', 0x7573657230, 'user0', 'user0@gmail.com', 'user0'),
(20, 'user3', 0x7573657233, 'user3', 'user3@gmail.com', 'user3'),
(21, 'amine', '', 'amine', 'afqf@qsdq.com', 'amine'),
(22, 'alice', 0x313233, 'alice', 'alice@email.com', 'alice'),
(23, 'user', 0x313233, 'contactamined@gmail.com', 'user', 'user'),
(48, 'bourai', 0x31323334, 'assiab', 'assizb@gmail.com', 'assia'),
(49, 'createteste', 0x313233, 'sdds', 'dsqds', 'gqdf'),
(50, 'moi', 0x6d6f69, 'moi', 'moi', 'moi'),
(51, 'lui', 0x313233, 'uito', 'fqfqs', 'l');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `check_user1` FOREIGN KEY (`user_id1`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `check_user2` FOREIGN KEY (`user_id2`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sessions`
--
ALTER TABLE `sessions`
  ADD CONSTRAINT `user_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
