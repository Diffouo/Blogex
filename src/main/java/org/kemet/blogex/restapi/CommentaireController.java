package org.kemet.blogex.restapi;
import java.util.List;

import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentaireController {
	
	@Autowired
	CommentaireRepository commentRepo;
	
	//Get all comments of the system
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Commentaire> getAllComments(){
		return commentRepo.findAll();
	}
}
