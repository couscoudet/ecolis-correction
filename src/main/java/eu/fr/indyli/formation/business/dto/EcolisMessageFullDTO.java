package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class EcolisMessageFullDTO extends EcolisMessageMediumDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876585131867899279L;

	public EcolisMessageFullDTO(Integer messageId, String corps, Date creationDate) {
		super(messageId, corps, creationDate);
	}
	
	public EcolisMessageFullDTO() {
        super();
    }

}
