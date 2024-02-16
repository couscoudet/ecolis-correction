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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.dto.UtilisateurAnnonceDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.DateUtils;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UtilisateurDAOTest {

	@Resource(name = EcolisConstantesDAO.USER_DAO_KEY)
	private IUtilisateurDAO userRepository;

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
		public void createUser() {
		  
		  	bcryptEncoder = new BCryptPasswordEncoder();
			Utilisateur utilisateur = new Utilisateur();
			
			utilisateur.setName("Abdou Zoyim Loti");
			utilisateur.setCivility("1");
			utilisateur.setEmail("abdou.loti@indyli-services.com");
			utilisateur.setEnabled((byte) 1);
			utilisateur.setLastConnection(new Date());
			utilisateur.setYearOfBirth(1992);
			utilisateur.setPhone("0398521647");
			utilisateur.setRegistrationDate(new Date());
			utilisateur.setLogin("loti");
			String encryptPassword = bcryptEncoder.encode("1234");
			utilisateur.setPassword(encryptPassword);
			
			utilisateur = this.userRepository.saveAndFlush(utilisateur);
			
			this.createUserId = utilisateur.getId();
		}
	  
	  @Test
	  public void testFindByIdWithSuccess() throws EcolisBusinessException {
		// Given
		Integer userId = this.userIdForAllTest;
		// When
		Utilisateur utilisateur = this.userRepository.findById(userId).orElse(null);
		// Then
		Assert.assertTrue(utilisateur.getId() == userId);
	  }
	  
	  @Test
	  public void testFindByFirstNameWithSuccess() throws EcolisBusinessException {
		// Given
		String userName = this.userNameAllTest;
		// When
		Utilisateur utilisateur = this.userRepository.findByName(userName);
		// Then
		Assert.assertTrue(utilisateur.getName() == userName);
	  }


	  @Test
	  public void testGetUserByEmail() throws EcolisBusinessException {
		  
		// Given
		String userEmail = this.userEmailAllTest;
	    // Recuperation par email
	    Utilisateur utilisateur = this.userRepository.findByEmail("abdou.zoyim@indyli-services.com");
	    //System.out.println("User authentifié :" + user);
	    Assert.assertTrue(utilisateur.getEmail() == userEmail);
	    //Assert.assertTrue(user != null);
	  }

	  @Test
	  public void testGetUserByEmailAndPassword() {
	    bcryptEncoder = new BCryptPasswordEncoder();
	    String login = "ema";
	    //String password = "macron";
	    Utilisateur utilisateur =
	        userRepository.findByLogin(login);
	    //$2a$10$LAvLA6jALgwY79MIHSwdnOX9UG1M4YhYrTWOAhT04cinf/Bk8O/AC
	    // Assert.
	    Assert.assertTrue(utilisateur != null);
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
	    for (UtilisateurAnnonceDTO utilisateurAnnonceDTO : liste) {
	      System.out.println(utilisateurAnnonceDTO);
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
	  
	  @Before
		public void prepareAllEntityBefore() throws EcolisBusinessException {
		  bcryptEncoder = new BCryptPasswordEncoder();
			Utilisateur utilisateur = new Utilisateur();
			
			utilisateur.setName("Abdou Zoyim Loti");
			utilisateur.setCivility("1");
			utilisateur.setEmail("abdou.zoyim@indyli-services.com");
			utilisateur.setEnabled((byte) 1);
			utilisateur.setLastConnection(new Date());
			utilisateur.setYearOfBirth(1992);
			utilisateur.setPhone("0398521647");
			utilisateur.setRegistrationDate(new Date());
			utilisateur.setLogin("loti");
			String encryptPassword = bcryptEncoder.encode("1234");
			utilisateur.setPassword(encryptPassword);
			
			utilisateur = userRepository.save(utilisateur);
			this.userIdForAllTest = utilisateur.getId();
			this.userEmailAllTest = utilisateur.getEmail();
			this.userNameAllTest = utilisateur.getName();
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
