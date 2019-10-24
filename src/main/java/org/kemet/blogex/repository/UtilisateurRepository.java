package org.kemet.blogex.repository;

import org.kemet.blogex.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Simply login a user with email address and password
    @Query(value = "SELECT u.id, u.nom, u.prenom, u.phone, u.mail, u.password, u.signupDate, u.groupe, u.comments, u.blogs" +
            " from Utilisateur u WHERE u.mail=?1 AND u.password=?2")
    Utilisateur findByEmailAndPassword(String email, String password);

}
