--
-- Contraintes pour la table `blog`
--
ALTER TABLE `blog`
    ADD CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`accid`) REFERENCES `utilisateur` (`accid`);

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
