CREATE TABLE IF NOT EXISTS `blog` (
      `blogid` bigint(20) NOT NULL AUTO_INCREMENT,
      `titre` varchar(40) NOT NULL,
      `contenu` text NOT NULL,
      `datepost` datetime NOT NULL,
      `accid` bigint(20) NOT NULL,
      PRIMARY KEY (`blogid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `commentaire` (
     `commentid` bigint(20) NOT NULL AUTO_INCREMENT,
     `commenttext` text NOT NULL,
     `commentdate` datetime NOT NULL,
     `blogid` bigint(20) NOT NULL,
     `accid` bigint(20) NOT NULL,
     PRIMARY KEY (`commentid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `groupe` (
    `groupid` int(4) NOT NULL AUTO_INCREMENT,
    `grouplabel` varchar(20) NOT NULL,
    PRIMARY KEY (`groupid`),
    UNIQUE KEY `grouplabel` (`grouplabel`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `utilisateur` (
     `accid` bigint(20) NOT NULL AUTO_INCREMENT,
     `accnom` varchar(50) NOT NULL,
     `accprenom` varchar(50) DEFAULT NULL,
     `accemail` varchar(130) NOT NULL,
     `accphone` varchar(20) NOT NULL,
     `acckey` varchar(50) NOT NULL,
     `accdate` datetime DEFAULT NULL,
     `groupid` int(4) NOT NULL,
     PRIMARY KEY (`accid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
