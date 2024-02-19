package eu.fr.indyli.formation.business.dto;


public class EcolisAdvertisingMediumDTO extends EcolisAdvertisingBasicDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937404262661193684L;
	
	private EcolisUserBasicDTO user;

	public EcolisAdvertisingMediumDTO() {
		super();
	}

	public EcolisUserBasicDTO getUser() {
		return user;
	}

	public void setUser(EcolisUserBasicDTO user) {
		this.user = user;
	}

}
