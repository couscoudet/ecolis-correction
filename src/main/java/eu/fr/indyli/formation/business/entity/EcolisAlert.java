package eu.fr.indyli.formation.business.entity;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Alert generated by hbm2java
 */
@Entity
@Table(name = "Alerte")
public class EcolisAlert implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4567351989909736794L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_alerte", unique = true, nullable = false)
	private Integer alertId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utilisateur")
	private EcolisUser user;
	
	@Column(name = "ville_depart")
	private String startCity;
	
	@Column(name = "ville_arrivee")
	private String endCity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_depot", nullable = false, length = 19)
	private Date depositDate;

	
	public EcolisAlert() {
		this.depositDate = new Date();
	}

	public EcolisAlert(EcolisUser user, String startCity, String endCity, Date depositDate) {
		super();
		this.user = user;
		this.startCity = startCity;
		this.endCity = endCity;
		this.depositDate = depositDate;
	}

	@Override
	public Integer getId() {
		return this.alertId;
	}

	@Override
	public void setId(Integer id) {
		this.alertId = id;
	}
	
	public EcolisUser getUser() {
		return user;
	}

	public void setUser(EcolisUser user) {
		this.user = user;
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

	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}

}
