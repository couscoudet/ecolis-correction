package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class EcolisCommentBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 626894809895143200L;
	private Integer commentId;
	private String corps;
	private Date creationDate;

	@Override
	public Integer getId() {
		return this.commentId;
	}

	@Override
	public void setId(Integer id) {
		this.commentId = id;
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
