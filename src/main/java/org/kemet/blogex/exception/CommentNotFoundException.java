package org.kemet.blogex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
	
	public CommentNotFoundException() {
		super("Aucun commentaire trouvé avec cet identifiant");
	}

}
