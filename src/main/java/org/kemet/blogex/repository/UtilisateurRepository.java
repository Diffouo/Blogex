package org.kemet.blogex.repository;

import java.util.List;

import org.kemet.blogex.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	//@Query(value = "SELECT u FROM Utilisateur u WHERE u.mail = ?1 AND u.password = ?2")
	Utilisateur findByMailAndPassword(String email, String password);
	
	Utilisateur findByMail(String mail);
	
	Utilisateur findByPhone(String phone);
	
	List<Utilisateur> findByNomContains(String nameLike);
}
