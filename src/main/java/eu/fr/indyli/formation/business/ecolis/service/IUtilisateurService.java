package eu.fr.indyli.formation.business.ecolis.service;

import java.util.Date;
import java.util.List;

import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.dto.UtilisateurBasicDTO;
import eu.fr.indyli.formation.business.dto.UtilisateurFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Utilisateur;

/**
 * 
 * @author CHZOME
 *
 */
public interface IUtilisateurService extends IAbstractServices<Utilisateur, UtilisateurBasicDTO, UtilisateurFullDTO, IUtilisateurDAO> {

	/**
     * recupere un utilisateur par son login et son password
     * @param login : Login de l'utilisateur qu'on souhaite recuperer
     * @param password : Password de l'utilisateur qu'on souhaite recuperer
     * @return : Utilisateur recherche
     */
    public Utilisateur findByLoginAndPassword(String login, String password) throws EcolisBusinessException ;
    /**
     * recupere un utilisateur par son login et son password
     * @param login : Login de l'utilisateur qu'on souhaite recuperer
     * @param password : Password de l'utilisateur qu'on souhaite recuperer
     * @return : Utilisateur recherche
     */
    public Utilisateur findByLogin(String login) throws EcolisBusinessException ;
    /**
     * Remonte les auteurs de comment postés après une date et ayant déposé une annonce pour ville arrivee donnée
     * @param pDatePivot : Date pivot de depot du commentaire
     * @param pVilleArrivee : Ville d'arrivée
     * @return
     */
    public List<Utilisateur> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot, String paramEndCity) throws EcolisBusinessException;
    /**
     * Recupere un utilisateur par son email
     * @param email
     * @return
     */
    public Utilisateur findByEmail(String email) throws EcolisBusinessException;
	
	/**
	 * Recherche les utilisateurs par email ou login
	 * @param login : Login pour lesquels on recherche des utilisateurs
	 * @param email : Email pour lesquels on recherche des utilisateurs
	 * @return
	 * @throws EcolisBusinessException
	 */
	 public List<Utilisateur> findByLoginOrEmail(String login, String email) throws EcolisBusinessException;
	 
	 /**
	     * Créer un utilisateur
	     * @param email
	     * @return
	     */
	 public Utilisateur createUser(Utilisateur user) throws EcolisBusinessException;
	 
	 /**
	     * Modifier un utilisateur
	     * @param email
	     * @return
	     */
	 public Utilisateur updateUser(Utilisateur user) throws EcolisBusinessException;
}
