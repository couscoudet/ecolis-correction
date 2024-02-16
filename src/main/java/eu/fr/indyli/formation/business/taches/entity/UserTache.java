package eu.fr.indyli.formation.business.taches.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "UTILISATEUR", catalog = "taches")
public class UserTache implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_user", unique = true, nullable = false)
	private Integer idUser;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "sexe")
	private Integer sexe;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "code_postal")
	private String codePostal;
	@Column(name = "code_departement")
	private Integer codeDepart;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_inscription")
	private Date dateInscription;
	
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Integer getSexe() {
		return sexe;
	}
	public void setSexe(Integer sexe) {
		this.sexe = sexe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public Integer getCodeDepart() {
		return codeDepart;
	}
	public void setCodeDepart(Integer codeDepart) {
		this.codeDepart = codeDepart;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
