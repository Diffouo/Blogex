package org.kemet.blogex.restapi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.entity.Blog;
import org.kemet.blogex.entity.Commentaire;
import org.kemet.blogex.entity.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.BlogRepository;
import org.kemet.blogex.repository.CommentaireRepository;
import org.kemet.blogex.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class BlogREST {
	
	@Autowired
	BlogRepository blogRepo;
	
	@Autowired
	CommentaireRepository commentRepo;
	
	@Autowired
	UtilisateurRepository userRepo;
	
	// Get the list of all posts
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Blog> getAllpost(){
		return blogRepo.findAll();
	}
	
	// Find a post by id
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Blog getPost(@PathVariable(name = "id") Integer id) {
		return blogRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Blog", "id", id.intValue()));
	}
	
	// Create a new post
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Blog createPost(@Valid @RequestBody Blog post) {
		return blogRepo.save(post);
	}
	
	// Update a post by id
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Blog updatePost(@PathVariable(name = "id") Integer id, @Valid @RequestBody Blog newPost) {
		Blog post =  blogRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id.intValue()));
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setPostOn(newPost.getPostOn());
		
		return blogRepo.save(post);
	}
	
	// Suppression d'un post
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deletePost(@PathVariable(name = "id") Integer id) {
		Blog post =  blogRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id.intValue()));
		blogRepo.delete(post);
		return ResponseEntity.ok().build();
	}
	
	// -------------------------   COMMENTS API ---------------------------
	
	//Create a new Comment for the specified blogID
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/{id}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Commentaire createComment(@Valid @RequestBody Commentaire comment, @PathVariable(name = "id") Integer blogID) {
		comment.setDateCmt(Calendar.getInstance().getTime());
		//comment = commentRepo.save(comment);
		return commentRepo.save(comment);
	}
	
	//Update a comment with given ID
	@RequestMapping(path = "/{id}/comments", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Commentaire updateComment( @PathVariable(name = "id") Integer idCom, @Valid @RequestBody Commentaire newComment) {
		Commentaire comment = commentRepo.findById(idCom).orElseThrow( ()-> new ResourceNotFound("Commentaire", "id", idCom.intValue()));
		
		if(newComment.getCommentby().getId().equals(comment.getCommentby().getId())) {
			comment.setText(newComment.getText());
			comment.setDateCmt(Calendar.getInstance().getTime());
			return commentRepo.save(comment);
		}
		Utilisateur user = userRepo.findById(newComment.getCommentby().getId()).orElseThrow( 
				()-> new ResourceNotFound("User", "id", newComment.getCommentby().getId().intValue()));
		throw new ResourceNotFound(user.getNom());
		
	}
	
	//Get all comments for a given idblog
	@RequestMapping(path = "/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Commentaire> getCommentsByBlog(@PathVariable(name = "id") Integer idBlog){
		Blog blog = blogRepo.findById(idBlog).orElseThrow( ()-> new ResourceNotFound("Blog", "id", idBlog.intValue()));
		List<Commentaire> comments = new ArrayList<>();
		for(Commentaire comment: blog.getComments()) {
			comments.add(comment);
		}
		return comments;
	}
	
	//Delete a comment with given ID
	@RequestMapping(path = "/{id}/comments", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteComment( @PathVariable(name = "id") Integer idCom) throws Exception {
		Commentaire comment = commentRepo.findById(idCom).orElseThrow( ()-> new ResourceNotFound("Commentaire", "id", idCom.intValue()));
		commentRepo.delete(comment);
		return ResponseEntity.ok().build();
		
	}
	
}
