package org.kemet.blogex.business;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.BlogRepository;
import org.kemet.blogex.repository.CommentaireRepository;
import org.kemet.blogex.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentaireRepository commentaireRepository;
    private final UtilisateurRepository utilisateurRepository;

    public BlogService(BlogRepository blogRepository, CommentaireRepository commentaireRepository, UtilisateurRepository utilisateurRepository) {
        this.blogRepository = blogRepository;
        this.commentaireRepository = commentaireRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // find all the posts
    public List<Blog> findAllPosts(){
        return blogRepository.findAll();
    }

    // find a post by ID
    public Blog findPostById(Long id){
        return blogRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Blog", "id", (int)id.longValue()));
    }

    // create a new Blog
    public Blog createPost(Blog post){
        return blogRepository.save(post);
    }

    // Update a post
    public Blog updatePost(Long id, Blog newPost){
        Blog post =  blogRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", (int)id.longValue()));
        post.setTitle(newPost.getTitle());
        post.setContent(newPost.getContent());
        post.setPostOn(newPost.getPostOn());
        return blogRepository.save(post);
    }

    // Delete a post
    public void deletePost(Long id){
        Blog post =  blogRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", (int)id.longValue()));
        blogRepository.delete(post);
    }
}

