--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`groupid`, `grouplabel`) VALUES
(1, 'Abonne'),
(2, 'Administrateur'),
(11, 'Controlleur'),
(12, 'Supperviseur');

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`accid`, `accnom`, `accprenom`, `accemail`, `accphone`, `acckey`, `accdate`, `groupid`) VALUES
(1, 'Diffouo', 'Jospin', 'jdiffouo@gmail.com', '237698757510', '0000', '2019-06-03 00:00:00', 1),
(2, 'Kamga', 'Pierre', 'pierrekamga@gmail.com', '237677869809', '0000', '2019-06-03 00:00:00', 1),
(3, 'admin test', 'prenom test', 'admin@test.com', '237672546721', '0000', '2019-06-03 11:09:00', 2),
(9, 'user 2Test01', '2Test01', '2Test01@gmail.com', '237699897865', '0000', '2019-06-12 00:00:00', 2),
(10, 'user 2Test02', '2Test02', '2Test02@gmail.com', '237698765432', '0000', '2019-06-12 00:00:00', 1);

--
-- Contenu de la table `blog`
--

INSERT INTO `blog` (`blogid`, `titre`, `contenu`, `datepost`, `accid`) VALUES
(1, 'Electricité: la BT', 'Lorem ipsum, bla bla bla bla', '2019-06-03 00:00:00', 1),
(2, 'Titre de test 01', 'Contenu de test 01', '2019-06-03 00:00:00', 2);;

--
-- Contenu de la table `commentaire`
--

INSERT INTO `commentaire` (`commentid`, `commenttext`, `commentdate`, `blogid`, `accid`) VALUES
(1, 'commentaire de test 01', '2019-06-03 09:00:00', 1, 1),
(2, 'comment updated', '2019-08-29 00:00:00', 1, 2),
(3, 'commentaire de test 03', '2019-06-03 13:08:00', 2, 3),
(4, 'commentaire de test 04', '2019-06-03 13:21:10', 2, 2),
(12, 'Commentaire de test 12', '2019-06-13 00:00:00', 1, 2),
(13, 'Commentaire de test 07', '2019-06-12 00:00:00', 1, 1),
(22, 'Commentaire de test 07', '2019-08-29 00:00:00', 2, 3),
(23, 'Commentaire de test 07', '2019-08-29 00:00:00', 2, 3),
(24, 'Un mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
(25, 'Commentaire test sur postMan', '2019-08-29 00:00:00', 2, 1),
(26, 'un autre comment 237 test sur postMan', '2019-08-29 00:00:00', 1, 9),
(27, 'Un autre mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
(28, 'Un autre mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
(29, 'Commentaire mock updated 2', '2019-08-29 00:00:00', 2, 2),
(30, 'Un autre mock commentaire, je vois le test 2', '2019-08-29 00:00:00', 2, 2),
(31, 'Un autre mock commentaire, je vois le test 2', '2019-08-29 00:00:00', 2, 2),
(34, 'Un autre mock commentaire, je vois le test 3', '2019-08-29 00:00:00', 2, 2),
(35, 'Voici les postMan Testn', '2019-08-29 00:00:00', 2, 9),
(36, 'Voici encore un autre postMan Test', '2019-08-29 00:00:00', 2, 10),
(37, 'Les comments again sur postMan pour status Test', '2019-08-30 00:00:00', 2, 9);


