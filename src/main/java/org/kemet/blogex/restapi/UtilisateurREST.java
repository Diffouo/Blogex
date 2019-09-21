package org.kemet.blogex.restapi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.Valid;

import org.kemet.blogex.entity.Blog;
import org.kemet.blogex.entity.Commentaire;
import org.kemet.blogex.entity.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UtilisateurREST {
	
	@Autowired
	UtilisateurRepository userRepo;
	
	@Autowired
	EntityManager em;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Utilisateur> getAllUsers(){
		return userRepo.findAll();
	}
	
	//Get all the Blogs posted by the user
	@RequestMapping(path = "/user/{id}/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Blog> getBlogByUser(@PathVariable(name = "id") Integer userID){
		Utilisateur user = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFound("User", "id", userID.intValue()));
		List<Blog> blogs = new ArrayList<>();
		for(Blog blog: user.getBlogs()) {
			blogs.add(blog);
		}
		return blogs;
	}
	
	//Get all the comments posted by the specified userID
	@RequestMapping(path = "/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Commentaire> getCommentsByUser(@PathVariable(name = "id") Integer userID){
		Utilisateur user = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFound("User", "id", userID.intValue()));
		List<Commentaire> comments = new ArrayList<>();
		for(Commentaire comment: user.getComments()) {
			comments.add(comment);
		}
		return comments;
	}
	
	// Find a user by his ID
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Utilisateur getUser(@PathVariable(name = "id") Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id.intValue()));
	}
	
	// Create a new user
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
		user.setSignupDate(Calendar.getInstance().getTime());
		return userRepo.save(user);
	}
	
	//Update user
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Utilisateur updateUser(@PathVariable(name = "id") Integer id, @Valid @RequestBody Utilisateur newUser) {
		Utilisateur user =  userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id.intValue()));
		user.setNom(newUser.getNom());
		user.setPrenom(newUser.getPrenom());
		user.setMail(newUser.getMail());
		user.setPhone(newUser.getPhone());
		user.setPassword(newUser.getPassword());
		return userRepo.save(user);
	}
	
	//Delete user
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer id) {
		Utilisateur user =  userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id.intValue()));
		userRepo.delete(user);
		return ResponseEntity.noContent().build();
	}
	
	//Login a user
	@RequestMapping(path = "/auth/{account}/{password}", method = RequestMethod.GET)
	public Utilisateur login(@PathVariable(name = "account") String compte, @PathVariable(name = "password") String password) {
		
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.mail = :mailparam AND u.password = :passparam");
        query.setParameter("mailparam", compte);
        query.setParameter("passparam", password);
        try{
            return ((Utilisateur)query.getSingleResult());
        }catch(NoResultException ex){
            return new Utilisateur(0);
        }
	}

}
