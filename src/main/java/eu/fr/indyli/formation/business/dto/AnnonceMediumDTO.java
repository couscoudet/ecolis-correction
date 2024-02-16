package eu.fr.indyli.formation.business.dto;


public class AnnonceMediumDTO extends AnnonceBasicDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937404262661193684L;
	
	private UtilisateurBasicDTO user;

	public AnnonceMediumDTO() {
		super();
	}

	public UtilisateurBasicDTO getUser() {
		return user;
	}

	public void setUser(UtilisateurBasicDTO user) {
		this.user = user;
	}

}
