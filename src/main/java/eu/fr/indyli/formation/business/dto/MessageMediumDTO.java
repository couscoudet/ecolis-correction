package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class MessageMediumDTO extends MessageBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4059534536424062682L;
	private AnnonceBasicDTO announcement;
	private UtilisateurBasicDTO user;
	
	public MessageMediumDTO(Integer messageId, String corps, Date creationDate) {
		super(messageId, corps, creationDate);
	}

	public AnnonceBasicDTO getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(AnnonceBasicDTO announcement) {
		this.announcement = announcement;
	}

	public UtilisateurBasicDTO getUser() {
		return user;
	}

	public void setUser(UtilisateurBasicDTO user) {
		this.user = user;
	}
}
