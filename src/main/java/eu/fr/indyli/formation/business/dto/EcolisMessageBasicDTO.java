package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class EcolisMessageBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8287926132646731587L;
	private Integer messageId;
	private String corps;
	private Date creationDate;
	
	public EcolisMessageBasicDTO() {
		super();
	}

	public EcolisMessageBasicDTO(Integer messageId, String corps, Date creationDate) {
		super();
		this.messageId = messageId;
		this.corps = corps;
		this.creationDate = creationDate;
	}

	@Override
	public Integer getId() {
		return this.messageId;
	}

	@Override
	public void setId(Integer id) {
		this.messageId = id;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
