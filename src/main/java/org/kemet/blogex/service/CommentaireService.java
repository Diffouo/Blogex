package org.kemet.blogex.service;

import java.util.Calendar;

import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.exception.CommentNotFoundException;
import org.kemet.blogex.repository.CommentaireRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

	private final CommentaireRepository commentaireRepository;
	
	public CommentaireService(CommentaireRepository commentaireRepository) {
		this.commentaireRepository= commentaireRepository;
	}
	
	public Commentaire createComment(Commentaire commentaire) {
		commentaire.setDateCmt(Calendar.getInstance().getTime());
		return commentaireRepository.save(commentaire);
	}
	
	public Commentaire findCommentById(Long id) {
		return commentaireRepository.findById(id).orElseThrow(CommentNotFoundException::new);
	}
	
	public Commentaire updateComment(Long id, Commentaire newComment) {
		Commentaire comment = findCommentById(id);
		comment.setText(newComment.getText());
		comment.setDateCmt(Calendar.getInstance().getTime());
		return commentaireRepository.save(comment);
	}
	
	public void deleteComment(Long id) {
		Commentaire comment = findCommentById(id);
		commentaireRepository.delete(comment);
	}
	
	public Long countAllComments() {
		return Long.parseLong(String.valueOf(commentaireRepository.findAll().size()));
	}

}
