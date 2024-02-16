package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class MessageFullDTO extends MessageMediumDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3876585131867899279L;

	public MessageFullDTO(Integer messageId, String corps, Date creationDate) {
		super(messageId, corps, creationDate);
	}

}
