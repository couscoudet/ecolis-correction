package eu.fr.indyli.formation.business.dto;


public class AlerteMediumDTO extends AlerteBasicDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -165064205622431579L;
	private UtilisateurBasicDTO user;

	public AlerteMediumDTO() {
		super();
	}

	public UtilisateurBasicDTO getUser() {
		return user;
	}

	public void setUser(UtilisateurBasicDTO user) {
		this.user = user;
	}

	
}
