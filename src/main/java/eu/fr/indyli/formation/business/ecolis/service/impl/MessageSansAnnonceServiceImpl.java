package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IMessageDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.entity.Message;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.MESSAGE_SANS_ANN_SERVICE_KEY)
public class MessageSansAnnonceServiceImpl implements IMessageService {

  @Resource(name = EcolisConstantes.EcolisConstantesDAO.MESSAGE_DAO_KEY)
  public IMessageDAO messageDAO = null;

  @Override
  public List<Message> findAll() {
    List<Message> msgList = messageDAO.findAll();
    for (Message message : msgList) {
      message.setAnnonce(null);
    }
    return msgList;
  }

  @Override
  public List<Message> getMessageByEmailUser(String email) throws EcolisBusinessException {
    // TODO Auto-generated method stub
    List<Message> msgList = this.messageDAO.getMessageByEmailUser(email);
    for (Message message : msgList) {
      message.setUtilisateur(null);
    }
    return msgList;
  }

  @Override
  public Message findById(Integer id) throws EcolisBusinessException {
    return this.messageDAO.findById(id).orElse(null);
  }


}
