package eu.fr.indyli.formation.business.entity;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Image generated by hbm2java
 */
@Entity
@Table(name = "Image")
public class EcolisImage implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055938211600328115L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_image", unique = true, nullable = false)
	private Integer imageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utilisateur", nullable = false)
	private EcolisUser user;
	
	@Column(name = "chemin")
	private String chemin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 19)
	private Date creationDate;
	
	@Override
	public Integer getId() {
		return this.imageId;
	}

	@Override
	public void setId(Integer id) {
		this.imageId = id;
	}

	public EcolisUser getUtilisateur() {
		return this.user;
	}

	public void setUtilisateur(EcolisUser user) {
		this.user = user;
	}

	public String getChemin() {
		return this.chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}