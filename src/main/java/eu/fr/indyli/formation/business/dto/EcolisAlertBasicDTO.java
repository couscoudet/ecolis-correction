package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class EcolisAlertBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2462924165275159158L;
	private Integer alertId;
	private String startCity;
	private String endCity;
	private Date depositDate;
	
	@Override
	public Integer getId() {
		return this.alertId;
	}
	
	@Override
	public void setId(Integer id) {
		this.alertId = id;
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
	
	public Date getDepositDate() {
		return depositDate;
	}
	
	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}
}
