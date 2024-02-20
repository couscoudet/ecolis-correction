package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IEcolisMessageDAO;
import eu.fr.indyli.formation.business.dto.EcolisMessageBasicDTO;
import eu.fr.indyli.formation.business.dto.EcolisMessageFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisMessageService;
import eu.fr.indyli.formation.business.entity.EcolisMessage;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import jakarta.annotation.Resource;

@Service(EcolisConstantesService.MESSAGE_SERVICE_KEY)
public class EcolisMessageServiceImpl extends AbstractServiceImpl<EcolisMessage, EcolisMessageBasicDTO, EcolisMessageFullDTO, IEcolisMessageDAO> implements IEcolisMessageService {

	@Resource(name = EcolisConstantesDAO.MESSAGE_DAO_KEY)
	public IEcolisMessageDAO ecolisMessageDAO;
	
	public EcolisMessageServiceImpl() {
		super(EcolisMessage.class, EcolisMessageBasicDTO.class, EcolisMessageFullDTO.class);
	}
	
	@Override
	public IEcolisMessageDAO getDAO() {
		return this.ecolisMessageDAO;
	}

	  @Override
	  public List<EcolisMessage> getMessageByEmailUser(String email) throws EcolisBusinessException {
	    // TODO Auto-generated method stub
	    List<EcolisMessage> msgList = this.ecolisMessageDAO.getMessageByEmailUser(email);
	    msgList = this.removeAttachedUsers(msgList);
	    return msgList;
	  }
	
	  private List<EcolisMessage> removeAttachedUsers(List<EcolisMessage> msgList) {
	    List<EcolisMessage> allMsgWithoutUsers = msgList.parallelStream().peek(msg -> {
	      msg.setUser(null);
	    }).collect(Collectors.toList());
	    return allMsgWithoutUsers;
	  }

}
