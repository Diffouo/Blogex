package org.kemet.blogex.api;
import java.util.List;

import org.kemet.blogex.business.CommentaireService;
import org.kemet.blogex.domain.Commentaire;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentaireController {

	private final CommentaireService commentaireService;

	public CommentaireController(CommentaireService commentaireService) {
		this.commentaireService = commentaireService;
	}

	@GetMapping
	public List<Commentaire> findAllComments(){
		return commentaireService.findAllComments();
	}
}
