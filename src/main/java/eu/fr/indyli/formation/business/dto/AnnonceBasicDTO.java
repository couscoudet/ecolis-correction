package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class AnnonceBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7015386163716241193L;
	private Integer announcementId;
	private String parcelContents;
	private long poids;
	private int unitePoids;
	private Date depositDate;
	private Date startDate;
	private Date endDate;
	private String startCity;
	private String endCity;
	private long prime;
	private int devise;
	private String statut;
	private int announcementType;
	private String detail;

	@Override
	public Integer getId() {
		return this.announcementId;
	}

	@Override
	public void setId(Integer id) {
		this.announcementId = id;
	}

	public String getParcelContents() {
		return parcelContents;
	}

	public void setParcelContents(String parcelContents) {
		this.parcelContents = parcelContents;
	}

	public long getPoids() {
		return poids;
	}

	public void setPoids(long poids) {
		this.poids = poids;
	}

	public int getUnitePoids() {
		return unitePoids;
	}

	public void setUnitePoids(int unitePoids) {
		this.unitePoids = unitePoids;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public long getPrime() {
		return prime;
	}

	public void setPrime(long prime) {
		this.prime = prime;
	}

	public int getDevise() {
		return devise;
	}

	public void setDevise(int devise) {
		this.devise = devise;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(int announcementType) {
		this.announcementType = announcementType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
