--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`grouplabel`) VALUES
('Abonne'),
('Administrateur');

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`accnom`, `accprenom`, `accemail`, `accphone`, `acckey`, `accdate`, `groupid`) VALUES
('Diffouo', 'Jospin', 'jdiffouo@gmail.com', '237698757510', '0000', '2019-06-03 00:00:00', 1),
('Kamga', 'Pierre', 'pierrekamga@gmail.com', '237677869809', '0000', '2019-06-03 00:00:00', 1),
('admin test', 'prenom test', 'admin@test.com', '237672546721', '0000', '2019-06-03 11:09:00', 2),
('user 2Test01', '2Test01', '2Test01@gmail.com', '237699897865', '0000', '2019-06-12 00:00:00', 2),
('user 2Test02', '2Test02', '2Test02@gmail.com', '237698765432', '0000', '2019-06-12 00:00:00', 1);

--
-- Contenu de la table `blog`
--

INSERT INTO `blog` (`titre`, `contenu`, `datepost`, `accid`) VALUES
('Electricité: la BT', 'Lorem ipsum, bla bla bla bla', '2019-06-03 00:00:00', 1),
('Titre de test 01', 'Contenu de test 01', '2019-06-03 00:00:00', 2);

