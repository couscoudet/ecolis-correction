package eu.fr.indyli.formation.business.dto;

public class CommentMediumDTO extends CommentBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4882102232234934823L;
	private UtilisateurBasicDTO user;
	
	
	public CommentMediumDTO() {
		super();
	}

	public UtilisateurBasicDTO getUser() {
		return user;
	}

	public void setUser(UtilisateurBasicDTO user) {
		this.user = user;
	}

}
