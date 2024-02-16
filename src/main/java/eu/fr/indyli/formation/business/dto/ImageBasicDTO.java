package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class ImageBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7132435449245070319L;
	private Integer imageId;
	private String chemin;
	private Date creationDate;

	@Override
	public Integer getId() {
		return this.imageId;
	}

	@Override
	public void setId(Integer id) {
		this.imageId = id;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
