package eu.fr.indyli.formation.business.dto;

import java.util.HashSet;
import java.util.Set;

public class AnnonceFullDTO extends AnnonceMediumDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4190825548881152752L;
	
	private Set<MessageBasicDTO> messages = new HashSet<MessageBasicDTO>(0);

	public AnnonceFullDTO() {
		super();
	}

	public Set<MessageBasicDTO> getMessages() {
		return messages;
	}

	public void setMessages(Set<MessageBasicDTO> messages) {
		this.messages = messages;
	}

}
