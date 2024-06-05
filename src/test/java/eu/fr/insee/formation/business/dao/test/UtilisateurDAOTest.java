package eu.fr.insee.formation.business.dao.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dao.IEcolisUserDAO;
import eu.fr.indyli.formation.business.dto.EcolisUserAdvertisingDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.EcolisAdvertising;
import eu.fr.indyli.formation.business.entity.EcolisUser;
import eu.fr.indyli.formation.business.utils.DateUtils;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("h2")
public class UtilisateurDAOTest {

	@Resource(name = EcolisConstantesDAO.USER_DAO_KEY)
	private IEcolisUserDAO userRepository;

	//@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	private Integer userIdForAllTest = null;
	private String userNameAllTest = null;
	private String userEmailAllTest = null;
	private Integer createUserId = null;

	 // @Test
	  @Rollback(true)
	  @Ignore
	  public void testEncryptAllPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();

	    List<EcolisUser> listUser = this.userRepository.findAll();
	    for (EcolisUser ecolisUser : listUser) {
	      String encryptPassword = bcryptEncoder.encode(ecolisUser.getLogin());
	      ecolisUser.setPassword(encryptPassword);
	      this.userRepository.saveAndFlush(ecolisUser);
	    }
	  }

	//  @Test
	  @Rollback(true)
	  public void testDecryptAllPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();
	    List<EcolisUser> listUser = this.userRepository.findAll();
	    for (EcolisUser ecolisUser : listUser) {
	      // String encryptPassword = bcryptEncoder.de(utilisateur.getLogin());
	      ecolisUser.setPassword(ecolisUser.getLogin());
	      this.userRepository.saveAndFlush(ecolisUser);
	    }
	  }
	  
	  @Test
		public void createUser() {
		  
		  	bcryptEncoder = new BCryptPasswordEncoder();
			EcolisUser ecolisUser = new EcolisUser();
			
			ecolisUser.setName("Abdou Zoyim Loti");
			ecolisUser.setCivility("1");
			ecolisUser.setEmail("abdou.loti@indyli-services.com");
			ecolisUser.setEnabled(true);
			ecolisUser.setLastConnection(new Date());
			ecolisUser.setYearOfBirth(1992);
			ecolisUser.setPhone("0398521647");
			ecolisUser.setRegistrationDate(new Date());
			ecolisUser.setLogin("loti");
			String encryptPassword = bcryptEncoder.encode("1234");
			ecolisUser.setPassword(encryptPassword);
			
			ecolisUser = this.userRepository.saveAndFlush(ecolisUser);
			
			this.createUserId = ecolisUser.getId();
		}
	  
	  @Test
	  public void testFindByIdWithSuccess() throws EcolisBusinessException {
		// Given
		Integer userId = this.userIdForAllTest;
		// When
		EcolisUser ecolisUser = this.userRepository.findById(userId).orElse(null);
		// Then
		Assert.assertTrue(ecolisUser.getId() == userId);
	  }
	  
	  @Test
	  public void testFindByFirstNameWithSuccess() throws EcolisBusinessException {
		// Given
		String userName = this.userNameAllTest;
		// When
		EcolisUser ecolisUser = this.userRepository.findByName(userName);
		// Then
		Assert.assertTrue(ecolisUser.getName() == userName);
	  }


	  @Test
	  public void testGetUserByEmail() throws EcolisBusinessException {
		  
		// Given
		String userEmail = this.userEmailAllTest;
	    // Recuperation par email
	    EcolisUser ecolisUser = this.userRepository.findByEmail("abdou.zoyim@indyli-services.com");
	    //System.out.println("User authentifié :" + user);
	    Assert.assertTrue(ecolisUser.getEmail() == userEmail);
	    //Assert.assertTrue(user != null);
	  }

	  @Test
	  public void testGetUserByEmailAndPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();
	    String login = "amb";
	    //String password = "macron";
	    EcolisUser ecolisUser =
	        userRepository.findByLogin(login);
	    //$2a$10$LAvLA6jALgwY79MIHSwdnOX9UG1M4YhYrTWOAhT04cinf/Bk8O/AC
	    // Assert.
	    Assert.assertTrue(ecolisUser != null);
	  }

	  @Test
	  public void testGetCommentAuthorByDateAndArrivalTown() {
	    Date datePivot = DateUtils.stringToDate("04/07/2015");
	    List<EcolisUser> liste =
	        this.userRepository.findAuthorsCommentByDateAndPostedAnnonce(datePivot, "Douala");
	    for (EcolisUser ecolisUser : liste) {
	      System.out.println(ecolisUser);
	    }
	    Assert.assertTrue(!CollectionUtils.isEmpty(liste));
	  }

//	  @Test
//	  public void testGetAllUserAvecAnnonces() throws EcolisBusinessException {
//	    List<EcolisUser> liste = this.userRepository.getAllUtilisateurAvecAnnonces();
//	    for (EcolisUser ecolisUserAdvertisingDTO : liste) {
//	      System.out.println(ecolisUserAdvertisingDTO);
//	    }
//	    Assert.assertTrue(!CollectionUtils.isEmpty(liste));
//	  }
	  
	  @Test
	  public void testGetAllUserAvecAnnonces() throws EcolisBusinessException {
	    List<EcolisUserAdvertisingDTO> liste = this.userRepository.getAllUtilisateurAvecAnnonces();
	    for (EcolisUserAdvertisingDTO ecolisUserAdvertisingDTO : liste) {
	      System.out.println(ecolisUserAdvertisingDTO);
	    }
	    Assert.assertTrue(!CollectionUtils.isEmpty(liste));
	  }

	  @Test
	  public void testGetUserParDomaine() throws EcolisBusinessException {
	    // Recuperation par email
	    List<EcolisUser> userList = userRepository.getUtilisateurParDomaineEtPossedantTel("com");
	    System.out.println("User authentifié :" + userList);
	    Assert.assertTrue(!CollectionUtils.isEmpty(userList));
	  }
	  
	  @Before
		public void prepareAllEntityBefore() throws EcolisBusinessException {
		  bcryptEncoder = new BCryptPasswordEncoder();
			EcolisUser ecolisUser = new EcolisUser();
			
			ecolisUser.setName("Abdou Zoyim Loti");
			ecolisUser.setCivility("1");
			ecolisUser.setEmail("abdou.zoyim@indyli-services.com");
			ecolisUser.setEnabled(true);
			ecolisUser.setLastConnection(new Date());
			ecolisUser.setYearOfBirth(1992);
			ecolisUser.setPhone("0398521647");
			ecolisUser.setRegistrationDate(new Date());
			ecolisUser.setLogin("loti");
			String encryptPassword = bcryptEncoder.encode("1234");
			ecolisUser.setPassword(encryptPassword);
			
			ecolisUser = userRepository.save(ecolisUser);
			this.userIdForAllTest = ecolisUser.getId();
			this.userEmailAllTest = ecolisUser.getEmail();
			this.userNameAllTest = ecolisUser.getName();
		} 
		
		@After
		public void deleteAllEntityAfter() throws AccessDeniedException, EcolisBusinessException {
			if (this.userIdForAllTest != null) {
				this.userRepository.deleteById(this.userIdForAllTest);
			}

			if (!Objects.isNull(this.createUserId)) { 
				this.userRepository.deleteById(this.createUserId);
			}
		}


}
