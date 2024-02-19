package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class EcolisMessageMediumDTO extends EcolisMessageBasicDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4059534536424062682L;
	private EcolisAdvertisingBasicDTO announcement;
	private EcolisUserBasicDTO user;
	
	
	public EcolisMessageMediumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EcolisMessageMediumDTO(Integer messageId, String corps, Date creationDate) {
		super(messageId, corps, creationDate);
	}

	public EcolisAdvertisingBasicDTO getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(EcolisAdvertisingBasicDTO announcement) {
		this.announcement = announcement;
	}

	public EcolisUserBasicDTO getUser() {
		return user;
	}

	public void setUser(EcolisUserBasicDTO user) {
		this.user = user;
	}
}
