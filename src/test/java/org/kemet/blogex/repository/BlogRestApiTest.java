package org.kemet.blogex.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.kemet.blogex.MotherTest;
import org.kemet.blogex.entity.Blog;
import org.kemet.blogex.entity.Commentaire;
import org.kemet.blogex.entity.Utilisateur;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BlogRestApiTest extends MotherTest {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void createComment() throws Exception {
		String uri = "/post/2/comment";
		Commentaire comment = new Commentaire();
		
		//comment.setId(5);
		comment.setText("Un autre mock commentaire, je vois le test 3");
		comment.setDateCmt(Calendar.getInstance().getTime());
		comment.setBlog(new Blog(2));
		comment.setCommentby(new Utilisateur(2));
		
		String inputJson = super.mapToJson(comment);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		/*String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Comment successfull created");*/
		
	}
	
	@Test
	public void getCommentsByBlog() throws Exception {
		String uri = "/post/1/comments";
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Commentaire[] listCom = super.mapFromJson(content, Commentaire[].class);
		assertTrue(listCom.length > 0);
		
	}
	
	@Test
	public void updateComment() throws Exception {
		String uri = "/post/29/comments";
		Commentaire comment = new Commentaire();
		comment.setId(29);
		comment.setText("Commentaire mock updated 2");
		comment.setDateCmt(Calendar.getInstance().getTime());
		comment.setBlog(new Blog(2));
		comment.setCommentby(new Utilisateur(2));
		
		String inputJson = super.mapToJson(comment);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Commentaire newCom = super.mapFromJson(content, Commentaire.class);
		assertTrue(comment.getText().equals(newCom.getText()));
	}
	
	@Test
	public void deleteComment() throws Exception {
		String uri = "/post/35/comments";
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	

}
