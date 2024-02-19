package eu.fr.indyli.formation.business.dto;


public class EcolisAlertMediumDTO extends EcolisAlertBasicDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -165064205622431579L;
	private EcolisUserBasicDTO user;

	public EcolisAlertMediumDTO() {
		super();
	}

	public EcolisUserBasicDTO getUser() {
		return user;
	}

	public void setUser(EcolisUserBasicDTO user) {
		this.user = user;
	}

	
}
