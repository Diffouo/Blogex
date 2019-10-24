package org.kemet.blogex.api;

import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.business.UtilisateurService;
import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.domain.Utilisateur;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UtilisateurController {
	
	private final UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService){
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping
	public List<Utilisateur> findAllUsers(){
		return utilisateurService.findAllUsers();
	}
	
	//Get all the Blogs posted by the user
	@GetMapping("/user/{id}/posts")
	public List<Blog> findBlogByUser(@PathVariable(name = "id") Long userID){
		return utilisateurService.findAllPostByUserID(userID);
	}
	
	//Get all the comments posted by the specified userID
	@GetMapping("/{id}/comments")
	public List<Commentaire> findCommentsByUser(@PathVariable(name = "id") Long userID){
		return utilisateurService.findAllCommentsFromUserID(userID);
	}
	
	// Find a user by his ID
	@GetMapping("/{id}")
	public Utilisateur findUserById(@PathVariable(name = "id") Long id) {
		return utilisateurService.findUserById(id);
	}
	
	// Create a new user
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
		return utilisateurService.createUser(user);
	}
	
	//Update user
	@PutMapping("/{id}")
	public Utilisateur updateUser(@PathVariable(name = "id") Long id, @Valid @RequestBody Utilisateur newUser) {
		return utilisateurService.updateUser(id, newUser);
	}
	
	//Delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
		utilisateurService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	//Login a user
	@GetMapping("/auth/{account}/{password}")
	public Utilisateur login(@PathVariable(name = "account") String compte, @PathVariable(name = "password") String password) {
		return utilisateurService.loginUser(compte, password);
	}

}
