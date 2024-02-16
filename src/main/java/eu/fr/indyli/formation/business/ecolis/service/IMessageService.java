package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.dao.IMessageDAO;
import eu.fr.indyli.formation.business.dto.MessageBasicDTO;
import eu.fr.indyli.formation.business.dto.MessageFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Message;

/**
 * 
 * @author CHZOME
 *
 */
public interface IMessageService extends IAbstractServices<Message, MessageBasicDTO, MessageFullDTO, IMessageDAO> {

  /**
   * 
   * @param email
   * @throws EcolisBusinessException
   */
  List<Message> getMessageByEmailUser(String email) throws EcolisBusinessException;

}
