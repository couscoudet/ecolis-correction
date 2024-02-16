package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Message;

/**
 * 
 * @author CHZOME
 *
 */
public interface IMessageService {

  /**
   * Retourne un message par son Id
   * 
   * @param id
   * @return
   * @throws EcolisBusinessException
   */
  public Message findById(Integer id) throws EcolisBusinessException;

  public List<Message> findAll();

  /**
   * 
   * @param email
   * @throws EcolisBusinessException
   */
  List<Message> getMessageByEmailUser(String email) throws EcolisBusinessException;


}
