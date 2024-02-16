package eu.fr.insee.formation.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.dto.UtilisateurAnnonceDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.DateUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UtilisateurDAOTest {

	@Autowired
	  IUtilisateurDAO userRepository;

	  // @Autowired
	  private BCryptPasswordEncoder bcryptEncoder;

	 // @Test
	  @Rollback(true)
	  @Ignore
	  public void testEncryptAllPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();

	    List<Utilisateur> listUser = this.userRepository.findAll();
	    for (Utilisateur utilisateur : listUser) {
	      String encryptPassword = bcryptEncoder.encode(utilisateur.getLogin());
	      utilisateur.setPassword(encryptPassword);
	      this.userRepository.saveAndFlush(utilisateur);
	    }
	  }

	//  @Test
	  @Rollback(true)
	  public void testDecryptAllPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();
	    List<Utilisateur> listUser = this.userRepository.findAll();
	    for (Utilisateur utilisateur : listUser) {
	      // String encryptPassword = bcryptEncoder.de(utilisateur.getLogin());
	      utilisateur.setPassword(utilisateur.getLogin());
	      this.userRepository.saveAndFlush(utilisateur);
	    }
	  }


	  @Test
	  public void testGetUserByEmail() {
	    // Recuperation par email
	    Utilisateur user = userRepository.findByEmail("christophe.zome@indyli-services.com");
	    System.out.println("User authentifié :" + user);
	    Assert.assertTrue(user != null);
	  }

	  //@Test
	  public void testGetUserByEmailAndPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();
	    Utilisateur user =
	        userRepository.findByLoginAndPassword("ema",
	            "macron");
	    //$2a$10$LAvLA6jALgwY79MIHSwdnOX9UG1M4YhYrTWOAhT04cinf/Bk8O/AC
	    // Assert.
	    Assert.assertTrue(user != null);
	  }

	  @Test
	  public void testGetCommentAuthorByDateAndArrivalTown() {
	    Date datePivot = DateUtils.stringToDate("04/07/2015");
	    List<Utilisateur> liste =
	        this.userRepository.findAuthorsCommentByDateAndPostedAnnonce(datePivot, "Douala");
	    for (Utilisateur utilisateur : liste) {
	      System.out.println(utilisateur);
	    }
	    Assert.assertTrue(!CollectionUtils.isEmpty(liste));
	  }

	  @Test
	  public void testGetAllUserAvecAnnonces() throws EcolisBusinessException {
	    List<UtilisateurAnnonceDTO> liste = this.userRepository.getAllUtilisateurAvecAnnonces();
	    for (UtilisateurAnnonceDTO userAnnonceDTO : liste) {
	      System.out.println(userAnnonceDTO);
	    }
	    Assert.assertTrue(!CollectionUtils.isEmpty(liste));
	  }

	  @Test
	  public void testGetUserParDomaine() throws EcolisBusinessException {
	    // Recuperation par email
	    List<Utilisateur> userList = userRepository.getUtilisateurParDomaineEtPossedantTel("com");
	    System.out.println("User authentifié :" + userList);
	    Assert.assertTrue(!CollectionUtils.isEmpty(userList));
	  }


}
