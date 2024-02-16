package eu.fr.indyli.formation.business.dto;

import java.util.HashSet;
import java.util.Set;

public class UtilisateurFullDTO extends UtilisateurMediumDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2589753652558007204L;
	private Set<AlerteBasicDTO> alerts = new HashSet<AlerteBasicDTO>(0);
	private Set<AnnonceBasicDTO> announcements = new HashSet<AnnonceBasicDTO>(0);
	private Set<MessageBasicDTO> messages = new HashSet<MessageBasicDTO>(0);
	private Set<CommentBasicDTO> comments = new HashSet<CommentBasicDTO>(0);
	private Set<ImageBasicDTO> images = new HashSet<ImageBasicDTO>(0);
	
	
	public UtilisateurFullDTO() {
		super();
	}

	public Set<AlerteBasicDTO> getAlerts() {
		return alerts;
	}

	public void setAlerts(Set<AlerteBasicDTO> alerts) {
		this.alerts = alerts;
	}

	public Set<AnnonceBasicDTO> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Set<AnnonceBasicDTO> announcements) {
		this.announcements = announcements;
	}

	public Set<MessageBasicDTO> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessageBasicDTO> messages) {
		this.messages = messages;
	}

	public Set<CommentBasicDTO> getComments() {
		return comments;
	}

	public void setComments(Set<CommentBasicDTO> comments) {
		this.comments = comments;
	}

	public Set<ImageBasicDTO> getImages() {
		return images;
	}

	public void setImages(Set<ImageBasicDTO> images) {
		this.images = images;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
