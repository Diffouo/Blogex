INSERT INTO `blog` (`titre`, `contenu`, `datepost`, `accid`) VALUES
('Electricité: la BT', 'Lorem ipsum, bla bla bla bla', '2019-06-03 00:00:00', 1),
('Titre de test 01', 'Contenu de test 01', '2019-06-03 00:00:00', 2);

--
-- Contenu de la table `commentaire`
--

INSERT INTO `commentaire` (`commenttext`, `commentdate`, `blogid`, `accid`) VALUES
('commentaire de test 01', '2019-06-03 09:00:00', 1, 1),
('comment updated', '2019-08-29 00:00:00', 1, 2),
('commentaire de test 03', '2019-06-03 13:08:00', 2, 3),
('commentaire de test 04', '2019-06-03 13:21:10', 2, 2),
('Commentaire de test 12', '2019-06-13 00:00:00', 1, 2),
('Commentaire de test 07', '2019-06-12 00:00:00', 1, 1),
('Commentaire de test 07', '2019-08-29 00:00:00', 2, 3),
('Commentaire de test 07', '2019-08-29 00:00:00', 2, 3),
('Un mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
('Commentaire test sur postMan', '2019-08-29 00:00:00', 2, 1),
('un autre comment 237 test sur postMan', '2019-08-29 00:00:00', 1, 9),
('Un autre mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
('Un autre mock commentaire, je vois le test', '2019-08-29 00:00:00', 2, 2),
('Commentaire mock updated 2', '2019-08-29 00:00:00', 2, 2),
('Un autre mock commentaire, je vois le test 2', '2019-08-29 00:00:00', 2, 2),
('Un autre mock commentaire, je vois le test 2', '2019-08-29 00:00:00', 2, 2),
('Un autre mock commentaire, je vois le test 3', '2019-08-29 00:00:00', 2, 2),
('Voici les postMan Testn', '2019-08-29 00:00:00', 2, 9),
('Voici encore un autre postMan Test', '2019-08-29 00:00:00', 2, 10),
('Les comments again sur postMan pour status Test', '2019-08-30 00:00:00', 2, 9);

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`grouplabel`) VALUES
('Abonne'),
('Administrateur'),
('Controlleur'),
('Supperviseur');

-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`accnom`, `accprenom`, `accemail`, `accphone`, `acckey`, `accdate`, `groupid`) VALUES
('Diffouo', 'Jospin', 'jdiffouo@gmail.com', '237698757510', '0000', '2019-06-03 00:00:00', 1),
('Kamga', 'Pierre', 'pierrekamga@gmail.com', '237677869809', '0000', '2019-06-03 00:00:00', 1),
('admin test', 'prenom test', 'admin@test.com', '237672546721', '0000', '2019-06-03 11:09:00', 2),
('user 2Test01', '2Test01', '2Test01@gmail.com', '237699897865', '0000', '2019-06-12 00:00:00', 2),
('user 2Test02', '2Test02', '2Test02@gmail.com', '237698765432', '0000', '2019-06-12 00:00:00', 1);
