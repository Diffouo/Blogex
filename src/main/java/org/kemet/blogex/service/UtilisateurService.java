package org.kemet.blogex.service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exception.GroupeNotFoundException;
import org.kemet.blogex.exception.UserAlreadyExistsException;
import org.kemet.blogex.exception.UserNotFoundException;
import org.kemet.blogex.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;
	
	public UtilisateurService(UtilisateurRepository utilisateurRepository ) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	public Utilisateur createUtilisateur(Utilisateur utilisateur) throws UserAlreadyExistsException {
		try {
			return utilisateurRepository.save(utilisateur);
		}
		catch(Exception exception) {
			throw new UserAlreadyExistsException(exception.getMessage(), exception.getCause());
		}
	}
	
	public Utilisateur changePassword(Long id, String newPassword) {
		Utilisateur user = findUserByID(id);
		user.setPassword(newPassword);
		return utilisateurRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		utilisateurRepository.deleteById(id);
	}
	
	public Utilisateur loginUser(String email, String password) {
		return utilisateurRepository.findByMailAndPassword(email, password);
	}
	
	public List<Utilisateur> findAllUsers() {
		return utilisateurRepository.findAll();
	}
	
	public Utilisateur findUserByID(Long id) {
		return utilisateurRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}
	
	public List<Blog> findBlogsByUser(Long userID) {
		return findUserByID(userID).getBlogs().stream().collect(Collectors.toList());
	}
	
	public List<Commentaire> findCommentsByUser(Long userID) {
		return findUserByID(userID).getComments().stream().collect(Collectors.toList());
	}
	
	public Utilisateur findUserByMail(String mail) {
		return utilisateurRepository.findByMail(mail);
	}
	
	public Utilisateur findUserByPhone(String phone) {
		return utilisateurRepository.findByPhone(phone);
	}
	
	public List<Utilisateur> findUserByNameLike(String nameLike) {
		return utilisateurRepository.findByNomContains(nameLike);
	}
}
