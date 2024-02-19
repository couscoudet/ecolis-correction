package eu.fr.indyli.formation.business.dto;

public class EcolisCommentMediumDTO extends EcolisCommentBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4882102232234934823L;
	private EcolisUserBasicDTO user;
	
	
	public EcolisCommentMediumDTO() {
		super();
	}

	public EcolisUserBasicDTO getUser() {
		return user;
	}

	public void setUser(EcolisUserBasicDTO user) {
		this.user = user;
	}

}
