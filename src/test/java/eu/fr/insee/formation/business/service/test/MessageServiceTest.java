package eu.fr.insee.formation.business.service.test;

import java.util.List;
import javax.annotation.Resource;
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
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.entity.Message;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MessageServiceTest {

  @Resource(name = EcolisConstantesService.MESSAGE_SERVICE_KEY)
  public IMessageService msgServiceAvecAnnonce = null;

  @Resource(name = EcolisConstantesService.MESSAGE_SANS_ANN_SERVICE_KEY)
  public IMessageService msgServiceSansAnnonce = null;

  public IMessageService messageService;

  @Test
  public void testRecupAllMessage() {
    messageService = this.msgServiceAvecAnnonce;
    List<Message> msgList = this.messageService.findAll();
    for (Message message : msgList) {
      Assert.assertTrue(message.getAnnonce() != null);
    }
    messageService = this.msgServiceSansAnnonce;
    msgList = this.messageService.findAll();
    for (Message message : msgList) {
      Assert.assertTrue(message.getAnnonce() == null);
    }
    Assert.assertTrue(!CollectionUtils.isEmpty(msgList));
  }

  @Test
  public void testRecupAllMessageByEmailId() throws EcolisBusinessException {
    List<Message> msgList = this.msgServiceAvecAnnonce.getMessageByEmailUser("czome@yahoo.fr");
    for (Message message : msgList) {
      System.out.println(message);
    }
    Assert.assertTrue(!CollectionUtils.isEmpty(msgList));
  }
}
