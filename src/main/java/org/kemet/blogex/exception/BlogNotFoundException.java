package org.kemet.blogex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {

	public BlogNotFoundException() {
		super("Aucun post trouvé");
	}
}
