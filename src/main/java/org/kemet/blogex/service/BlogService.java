package org.kemet.blogex.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.exception.BlogNotFoundException;
import org.kemet.blogex.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	private final BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public Blog createBlog(Blog blog) {
		return blogRepository.save(blog);
	}
	
	public List<Blog> findAllPost(){
		return blogRepository.findAll();
	}
	
	public Blog findBlogById(Long id) {
		return blogRepository.findById(id).orElseThrow(BlogNotFoundException::new);
	}
	
	public Blog updateBlog(Long id, Blog newBlog) {
		Blog blog = findBlogById(id);
		blog.setTitle(newBlog.getTitle());
		blog.setContent(newBlog.getContent());
		blog.setPostOn(Calendar.getInstance().getTime());
		return blogRepository.save(blog);
	}
	
	public void deleteBlog(Long id) {
		Blog blog = findBlogById(id);
		blogRepository.delete(blog);
	}
	
	public List<Commentaire> findCommentsOfBlog(Long blogID){
		Blog blog = findBlogById(blogID);
		return blog.getComments().stream().collect(Collectors.toList());
	}
	
	public Long countCommentsOfBlog(Long blogID) {
		Blog blog = findBlogById(blogID);
		return Long.parseLong(String.valueOf(blog.getComments().stream().collect(Collectors.toList()).size()));
	}
	

}
