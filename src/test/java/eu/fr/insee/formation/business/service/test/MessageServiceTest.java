package eu.fr.insee.formation.business.service.test;

import java.util.List;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dto.EcolisMessageFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisMessageService;
import eu.fr.indyli.formation.business.entity.EcolisMessage;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MessageServiceTest {

	  @Resource(name = EcolisConstantesService.MESSAGE_SERVICE_KEY)
	  public IEcolisMessageService msgServiceAvecAnnonce;
	
	  @Resource(name = EcolisConstantesService.MESSAGE_SANS_ANN_SERVICE_KEY)
	  public IEcolisMessageService msgServiceSansAnnonce;
	  
	  public IEcolisMessageService ecolisMessageService;
	
	  @Test 
	  public void testRecupAllMessage() {
		ecolisMessageService = this.msgServiceAvecAnnonce;  
	    List<EcolisMessageFullDTO> msgList = this.ecolisMessageService.findAllFull();
	    
	    for (EcolisMessageFullDTO message : msgList) {
	      Assert.assertTrue(message.getAnnouncement() != null);
	    }
	    
	    /*messageService = this.msgServiceSansAnnonce;
	    msgList = this.messageService.findAllFull();
	    for (MessageFullDTO message : msgList) {
	      Assert.assertTrue(message.getAnnouncement() == null);
	    }*/
	    
	    Assert.assertTrue(!CollectionUtils.isEmpty(msgList));
	  }
	
	  @Test
	  public void testRecupAllMessageByEmailId() throws EcolisBusinessException {
	    List<EcolisMessage> msgList = this.msgServiceAvecAnnonce.getMessageByEmailUser("czome@yahoo.fr");
	    for (EcolisMessage ecolisMessage : msgList) {
	      System.out.println(ecolisMessage);
	    }
	    Assert.assertTrue(!CollectionUtils.isEmpty(msgList));
	  }
}
