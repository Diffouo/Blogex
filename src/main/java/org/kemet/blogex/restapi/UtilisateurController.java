package org.kemet.blogex.restapi;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exception.UserAlreadyExistsException;
import org.kemet.blogex.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UtilisateurController {
	
	private final UtilisateurService utilisateurService;
	
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	// Create a new user
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Utilisateur createUser(@Valid @RequestBody Utilisateur utilisateur) throws UserAlreadyExistsException {
		utilisateur.setSignupDate(Calendar.getInstance().getTime());
		return utilisateurService.createUtilisateur(utilisateur);
	}
	
	@GetMapping
	public List<Utilisateur> findAllUsers(){
		return utilisateurService.findAllUsers();
	}
	
	//Get all the Blogs posted by the user
	@GetMapping("/{id}/posts")
	public List<Blog> getBlogByUser(@PathVariable(name = "id") Long userID){
		return utilisateurService.findBlogsByUser(userID);
	}
	
	//Get all the comments posted by the specified userID
	@GetMapping("/{id}/comments")
	public List<Commentaire> findCommentsByUser(@PathVariable(name = "id") Long userID){
		return utilisateurService.findCommentsByUser(userID);
	}
	
	//Update user
	@PutMapping("/{id}")
	public Utilisateur updateCredentials(@PathVariable(name = "id") Long id, String newPAssword) {
		return utilisateurService.changePassword(id, newPAssword);
	}
	
	//Delete user
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		utilisateurService.deleteUser(id);
	}
	
	//Login a user
	@GetMapping("/auth")
	public Utilisateur login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		return utilisateurService.loginUser(email, password);
		
	}

}
