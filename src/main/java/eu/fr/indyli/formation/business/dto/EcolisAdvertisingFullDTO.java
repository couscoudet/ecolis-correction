package eu.fr.indyli.formation.business.dto;

import java.util.HashSet;
import java.util.Set;

public class EcolisAdvertisingFullDTO extends EcolisAdvertisingMediumDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4190825548881152752L;
	
	private Set<EcolisMessageBasicDTO> messages = new HashSet<EcolisMessageBasicDTO>(0);

	public EcolisAdvertisingFullDTO() {
		super();
	}

	public Set<EcolisMessageBasicDTO> getMessages() {
		return messages;
	}

	public void setMessages(Set<EcolisMessageBasicDTO> messages) {
		this.messages = messages;
	}

}
