package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IMessageDAO;
import eu.fr.indyli.formation.business.dto.MessageBasicDTO;
import eu.fr.indyli.formation.business.dto.MessageFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.entity.Message;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.MESSAGE_SERVICE_KEY)
public class MessageServiceImpl extends AbstractServiceImpl<Message, MessageBasicDTO, MessageFullDTO, IMessageDAO> implements IMessageService {

	@Resource(name = EcolisConstantesDAO.MESSAGE_DAO_KEY)
	public IMessageDAO messageDAO;
	
	public MessageServiceImpl() {
		super(Message.class, MessageBasicDTO.class, MessageFullDTO.class);
	}
	
	@Override
	public IMessageDAO getDAO() {
		return this.messageDAO;
	}

	  @Override
	  public List<Message> getMessageByEmailUser(String email) throws EcolisBusinessException {
	    // TODO Auto-generated method stub
	    List<Message> msgList = this.messageDAO.getMessageByEmailUser(email);
	    msgList = this.removeAttachedUsers(msgList);
	    return msgList;
	  }
	
	  private List<Message> removeAttachedUsers(List<Message> msgList) {
	    List<Message> allMsgWithoutUsers = msgList.parallelStream().peek(msg -> {
	      msg.setUser(null);
	    }).collect(Collectors.toList());
	    return allMsgWithoutUsers;
	  }

}
