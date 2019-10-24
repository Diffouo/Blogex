package org.kemet.blogex.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.kemet.blogex.business.BlogService;
import org.kemet.blogex.business.CommentaireService;
import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class BlogController {
	
	private final BlogService blogService;
	private final CommentaireService commentaireService;

	public BlogController(BlogService blogService, CommentaireService commentaireService) {
		this.blogService = blogService;
		this.commentaireService = commentaireService;
	}

	// Get the list of all posts
	@GetMapping
	public List<Blog> findAllposts(){
		return blogService.findAllPosts();
	}
	
	// Find a post by id
	@GetMapping("/{id}")
	public Blog getPost(@PathVariable(name = "id") Long id) {
		return blogService.findPostById(id);
	}
	
	// Create a new post
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Blog createPost(@Valid @RequestBody Blog post) {
		return blogService.createPost(post);
	}
	
	// Update a post by id
	@PutMapping("/{id}")
	public Blog updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody Blog newPost) {
		return blogService.updatePost(id, newPost);
	}
	
	// Suppression d'un post
	@DeleteMapping("/{id}")
	public ResponseEntity deletePost(@PathVariable(name = "id") Long id) {
		blogService.deletePost(id);
		return ResponseEntity.noContent().build();
	}
	
	// -------------------------   COMMENTS API   ---------------------------
	
	//Create a new Comment for the specified blogID
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/{id}/comment")
	public Commentaire createComment(@Valid @RequestBody Commentaire comment, @PathVariable(name = "id") Long blogID) {
		return commentaireService.createComment(blogID, comment);
	}
	
	//Update a comment with given ID
	@PutMapping("/{id}/comments")
	public Commentaire updateComment( @PathVariable(name = "id") Long idCom, @Valid @RequestBody Commentaire newComment) {
		return commentaireService.updateComment(idCom, newComment);
	}
	
	//Get all comments for a given idblog
	@GetMapping("/{id}/comments")
	public List<Commentaire> findCommentsByBlog(@PathVariable(name = "id") Long idBlog){
		Blog blog = blogService.findPostById(idBlog);
		return blog.getComments().stream().collect(Collectors.toList());
	}
	
	//Delete a comment with given ID
	@DeleteMapping("/{id}/comments")
	public ResponseEntity deleteComment( @PathVariable(name = "id") Long idCom) {
		commentaireService.deleteComment(idCom);
		return ResponseEntity.noContent().build();
	}
	
}
