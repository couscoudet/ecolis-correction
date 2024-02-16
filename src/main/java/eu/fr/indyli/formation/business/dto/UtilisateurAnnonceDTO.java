package eu.fr.indyli.formation.business.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UtilisateurAnnonceDTO {

	private Integer idUtilisateur;
	private String nom;
	private Integer nbAnnonces;
	
	public UtilisateurAnnonceDTO() {	}
	
	public UtilisateurAnnonceDTO(Integer idUtilisateur, String nom, Integer nbAnnonces) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.nbAnnonces = nbAnnonces;
	}
	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getNbAnnonces() {
		return nbAnnonces;
	}
	public void setNbAnnonces(Integer nbAnnonces) {
		this.nbAnnonces = nbAnnonces;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
