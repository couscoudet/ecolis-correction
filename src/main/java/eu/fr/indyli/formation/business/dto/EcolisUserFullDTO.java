package eu.fr.indyli.formation.business.dto;

import java.util.HashSet;
import java.util.Set;

public class EcolisUserFullDTO extends EcolisUserMediumDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2589753652558007204L;
	private Set<EcolisAlertBasicDTO> alerts = new HashSet<EcolisAlertBasicDTO>(0);
	private Set<EcolisAdvertisingBasicDTO> announcements = new HashSet<EcolisAdvertisingBasicDTO>(0);
	private Set<EcolisMessageBasicDTO> messages = new HashSet<EcolisMessageBasicDTO>(0);
	private Set<EcolisCommentBasicDTO> comments = new HashSet<EcolisCommentBasicDTO>(0);
	private Set<EcolisImageBasicDTO> images = new HashSet<EcolisImageBasicDTO>(0);
	
	
	public EcolisUserFullDTO() {
		super();
	}

	public Set<EcolisAlertBasicDTO> getAlerts() {
		return alerts;
	}

	public void setAlerts(Set<EcolisAlertBasicDTO> alerts) {
		this.alerts = alerts;
	}

	public Set<EcolisAdvertisingBasicDTO> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Set<EcolisAdvertisingBasicDTO> announcements) {
		this.announcements = announcements;
	}

	public Set<EcolisMessageBasicDTO> getMessages() {
		return messages;
	}

	public void setMessages(Set<EcolisMessageBasicDTO> messages) {
		this.messages = messages;
	}

	public Set<EcolisCommentBasicDTO> getComments() {
		return comments;
	}

	public void setComments(Set<EcolisCommentBasicDTO> comments) {
		this.comments = comments;
	}

	public Set<EcolisImageBasicDTO> getImages() {
		return images;
	}

	public void setImages(Set<EcolisImageBasicDTO> images) {
		this.images = images;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
