package eu.fr.indyli.formation.business.dto;

public class ImageMediumDTO extends ImageBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166542980841246553L;
	private UtilisateurBasicDTO user;

	public ImageMediumDTO() {
		super();
	}

	public UtilisateurBasicDTO getUser() {
		return user;
	}

	public void setUser(UtilisateurBasicDTO user) {
		this.user = user;
	}
	
	

}
