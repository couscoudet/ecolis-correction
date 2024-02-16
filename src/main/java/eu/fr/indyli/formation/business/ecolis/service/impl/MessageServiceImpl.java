package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IMessageDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.entity.Message;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.MESSAGE_SERVICE_KEY)
public class MessageServiceImpl implements IMessageService {

  @Resource(name = EcolisConstantes.EcolisConstantesDAO.MESSAGE_DAO_KEY)
  public IMessageDAO messageDAO = null;

  @Override
  public List<Message> findAll() {
    List<Message> allMsg = messageDAO.findAll();
    allMsg = this.removeAttachedUsers(allMsg);
    return allMsg;
  }

  @Override
  public List<Message> getMessageByEmailUser(String email) throws EcolisBusinessException {
    // TODO Auto-generated method stub
    List<Message> msgList = this.messageDAO.getMessageByEmailUser(email);
    msgList = this.removeAttachedUsers(msgList);
    return msgList;
  }

  @Override
  public Message findById(Integer id) throws EcolisBusinessException {
    return this.messageDAO.findById(id).orElse(null);
  }

  private List<Message> removeAttachedUsers(List<Message> msgList) {
    List<Message> allMsgWithoutUsers = msgList.parallelStream().peek(msg -> {
      msg.setUtilisateur(null);
    }).collect(Collectors.toList());
    return allMsgWithoutUsers;
  }


}
