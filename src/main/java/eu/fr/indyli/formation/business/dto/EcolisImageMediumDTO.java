package eu.fr.indyli.formation.business.dto;

public class EcolisImageMediumDTO extends EcolisImageBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166542980841246553L;
	private EcolisUserBasicDTO user;

	public EcolisImageMediumDTO() {
		super();
	}

	public EcolisUserBasicDTO getUser() {
		return user;
	}

	public void setUser(EcolisUserBasicDTO user) {
		this.user = user;
	}
	
	

}
