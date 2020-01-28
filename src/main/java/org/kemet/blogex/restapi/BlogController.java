package org.kemet.blogex.restapi;

import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.service.BlogService;
import org.kemet.blogex.service.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Blog> getAllpost(){
		return blogService.findAllPost();
	}
	
	// Create a new post
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Blog createPost(@Valid @RequestBody Blog blog) {
		return blogService.createBlog(blog);
	}
	
	// Update a post by id
	@PutMapping("/{id}")
	public Blog updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody Blog newBlog) {
		return blogService.updateBlog(id, newBlog);
	}
	
	// Suppression d'un post
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteBlog(@PathVariable(name = "id") Long id) {
		blogService.deleteBlog(id);
	}

	//Create a new Comment for the specified blogID
	@PostMapping("/{id}/comment")
	public ResponseEntity<Commentaire> createComment(@Valid @RequestBody Commentaire comment, @PathVariable(name = "id") Long id) {
		Blog blog = blogService.findBlogById(id); // will throw BlogNotFoundException with 404 if there is no blog with this id
		comment.setBlog(blog);
		return ResponseEntity.status(HttpStatus.CREATED).body(commentaireService.createComment(comment));
	}
	
	//Update a comment with given ID
	@PutMapping("/{id}/comments")
	public Commentaire updateComment( @PathVariable(name = "id") Long commentID, @Valid @RequestBody Commentaire newComment) {
		return commentaireService.updateComment(commentID, newComment);
	}
	
	//Get all comments for a given idblog
	@GetMapping("/{id}/comments")
	public List<Commentaire> findCommentsOfBlog(@PathVariable(name = "id") Long blogID){
		return blogService.findCommentsOfBlog(blogID);
	}
	
	//Delete a comment with given ID
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}/comments")
	public void deleteComment( @PathVariable(name = "id") Long commentID) {
		commentaireService.deleteComment(commentID);		
	}
	
}
