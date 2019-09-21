CREATE TABLE IF NOT EXISTS `blog` (
      `blogid` bigint(20) NOT NULL AUTO_INCREMENT,
      `titre` varchar(40) NOT NULL,
      `contenu` text NOT NULL,
      `datepost` datetime NOT NULL,
      `accid` bigint(20) NOT NULL,
      PRIMARY KEY (`blogid`),
      KEY `accid` (`accid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

CREATE TABLE IF NOT EXISTS `commentaire` (
     `commentid` bigint(20) NOT NULL AUTO_INCREMENT,
     `commenttext` text NOT NULL,
     `commentdate` datetime NOT NULL,
     `blogid` bigint(20) NOT NULL,
     `accid` bigint(20) NOT NULL,
     PRIMARY KEY (`commentid`),
     KEY `blogid` (`blogid`),
     KEY `accid` (`accid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=38 ;

CREATE TABLE IF NOT EXISTS `groupe` (
    `groupid` int(4) NOT NULL AUTO_INCREMENT,
    `grouplabel` varchar(20) NOT NULL,
    PRIMARY KEY (`groupid`),
    UNIQUE KEY `grouplabel` (`grouplabel`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `utilisateur` (
     `accid` bigint(20) NOT NULL AUTO_INCREMENT,
     `accnom` varchar(50) NOT NULL,
     `accprenom` varchar(50) DEFAULT NULL,
     `accemail` varchar(130) NOT NULL,
     `accphone` varchar(20) NOT NULL,
     `acckey` varchar(50) NOT NULL,
     `accdate` datetime DEFAULT NULL,
     `groupid` int(4) NOT NULL,
     PRIMARY KEY (`accid`),
     KEY `groupid` (`groupid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Contraintes pour la table `blog`
--
# ALTER TABLE `blog`
#     ADD CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`accid`) REFERENCES `utilisateur` (`accid`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
    ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`),
    ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`accid`) REFERENCES `utilisateur` (`accid`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`groupid`) REFERENCES `groupe` (`groupid`);
