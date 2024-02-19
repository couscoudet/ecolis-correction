package eu.fr.indyli.formation.business.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.fr.indyli.formation.business.entity.IEntity;

public class EcolisUserAdvertisingDTO implements IEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2390441265532072535L;
	private Integer userId;
	private String name;
	private Integer nbAnnouncements;
	
	public EcolisUserAdvertisingDTO() {	}
	
	public EcolisUserAdvertisingDTO(Integer userId, String name, Integer nbAnnouncements) {
		super();
		this.userId = userId;
		this.name = name;
		this.nbAnnouncements = nbAnnouncements;
	}
	
	@Override
	public Integer getId() {
		return this.userId;
	}

	@Override
	public void setId(Integer id) {
		this.userId = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNbAnnouncements() {
		return nbAnnouncements;
	}

	public void setNbAnnouncements(Integer nbAnnouncements) {
		this.nbAnnouncements = nbAnnouncements;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
