package eu.fr.indyli.formation.business.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.EcolisMessage;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

@Repository(EcolisConstantes.EcolisConstantesDAO.MESSAGE_DAO_KEY)
public interface IEcolisMessageDAO extends JpaRepository<EcolisMessage, Integer>{

	/**
	 * Modifie le corps des messages d'un utilisateur postés après une certaine date
	 * @param userId : Identifiant de l'utilisateur
	 * @param dtCreation : Date de creation du message
	 * @param newMsg : Nouveau message de l'annonce
	 * @throws EcolisBusinessException
	 */
	@Modifying
	@Query("update EcolisMessage msg set msg.corps = ?1 where msg.user.userId=?2 and msg.creationDate>=?3 ")
	public void setMessageBodyByUserIdAndCreatedDate(String newMsg, Integer userId, Date creationDate) throws EcolisBusinessException;
	
	@Query("from EcolisMessage m where m.user.email =:paramEmail")
	public List<EcolisMessage> getMessageByEmailUser(@Param("paramEmail") String email) throws EcolisBusinessException;
}
