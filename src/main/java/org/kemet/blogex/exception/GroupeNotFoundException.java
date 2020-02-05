package org.kemet.blogex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GroupeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupeNotFoundException(String value) {
		super(String.format("Aucun groupe trouvé dont le nom est %s ", value));
	}
	
	public GroupeNotFoundException(Integer value) {
		super(String.format("Aucun groupe trouvé dont l'identifiant est %s ", String.valueOf(value)));
	}
	
	public GroupeNotFoundException() {
		super("Aucun groupe trouvé");
	}
}
