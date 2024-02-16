package eu.fr.indyli.formation.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.business.dto.UtilisateurAnnonceDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

@Repository(EcolisConstantes.EcolisConstantesDAO.USER_DAO_KEY)
public interface IUtilisateurDAO extends JpaRepository<Utilisateur, Integer>{
	/**
     * recupere un utilisateur par son login et son password
     * @param login : Login de l'utilisateur qu'on souhaite recuperer
     * @param password : Password de l'utilisateur qu'on souhaite recuperer
     * @return : Utilisateur recherche
     */
    public Utilisateur findByLoginAndPassword(String login,String password);
    /**
     * Remonte les auteurs de comment postés après une date et ayant déposé une annonce pour ville arrivee donnée
     * @param pDatePivot : Date pivot de depot du commentaire
     * @param pVilleArrivee : Ville d'arrivée
     * @return
     */
    @Query("select user from Utilisateur user join fetch user.comments as cmt join fetch user.annonces as an where cmt.dateCreation >=:paramDatePivot and an.villeArrivee like %:paramVilleArrivee%")
    public List<Utilisateur> findAuthorsCommentByDateAndPostedAnnonce(@Param("paramDatePivot") Date paramDatePivot,@Param("paramVilleArrivee") String paramVilleArrivee);
    
    @Query(value = "SELECT * FROM Utilisateur WHERE EMAIL = ?1", nativeQuery = true)
    public Utilisateur findByEmail(String email);
    
    @Query(value = "SELECT * FROM Utilisateur WHERE LOGIN = ?1", nativeQuery = true)
    public Utilisateur findByLogin(String login);
    
    @Query(name="findUserAndAnnoncesCreatedQuery", nativeQuery = true)
    public List<UtilisateurAnnonceDTO> getAllUtilisateurAvecAnnonces() throws EcolisBusinessException;
    
    /**
     * Renvoie tous les utilisateurs possedant un telephone et dont l'adresse mail est d'un domaine donné
     * @param domaineP : Domaine pour lequel on recherche des utilisateurs
     * @return
     * @throws EcolisBusinessException
     */
    @Query("select user from Utilisateur user where user.email like %:paramDomaine and user.telephone is not null")
    public List<Utilisateur> getUtilisateurParDomaineEtPossedantTel(@Param("paramDomaine") String domaineP) throws EcolisBusinessException;
    
    /**
     * Recherche si un login ou email n'est pas déjà occupé
     * @param login : Login à chercher
     * @param email  : email à chercher
     * @return
     * @throws EcolisBusinessException
     */
   // @Query("select u from Utilisateur user where user.email =:paramDomaine or user.login=:paramEmail")
    public List<Utilisateur> findByLoginOrEmail(String login,String email) throws EcolisBusinessException;
}
