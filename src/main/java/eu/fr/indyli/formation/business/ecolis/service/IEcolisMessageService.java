package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.dao.IEcolisMessageDAO;
import eu.fr.indyli.formation.business.dto.EcolisMessageBasicDTO;
import eu.fr.indyli.formation.business.dto.EcolisMessageFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.EcolisMessage;

/**
 * 
 * @author CHZOME
 *
 */
public interface IEcolisMessageService extends IAbstractServices<EcolisMessage, EcolisMessageBasicDTO, EcolisMessageFullDTO, IEcolisMessageDAO> {

  /**
   * 
   * @param email
   * @throws EcolisBusinessException
   */
  List<EcolisMessageBasicDTO> getMessageByEmailUser(String email) throws EcolisBusinessException;

}
