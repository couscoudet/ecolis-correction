package eu.fr.insee.formation.business.service.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dto.UtilisateurBasicDTO;
import eu.fr.indyli.formation.business.dto.UtilisateurFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IUtilisateurService;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UtilisateurServiceTest {

	@Resource(name = EcolisConstantesService.USER_SERVICE_KEY)
    IUtilisateurService userService;
	
	private Integer userIdForAllTest = null;
	private Integer createUserId = null;
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Test
	public void createUserId() throws EcolisBusinessException {
		UtilisateurFullDTO user = new UtilisateurFullDTO();
		bcryptEncoder = new BCryptPasswordEncoder();
		
		user.setName("Abdou Zoyim Loti");
		user.setCivility("1");
		user.setEmail("abdou.loti@indyli-services.com");
		user.setEnabled((byte) 1);
		user.setLastConnection(new Date());
		user.setYearOfBirth(1992);
		user.setPhone("0398521647");
		user.setRegistrationDate(new Date());
		user.setLogin("loti");
		String encryptPassword = bcryptEncoder.encode("1234");
		user.setPassword(encryptPassword);
		
		user = userService.create(user);
		this.createUserId = user.getId();
	}
	
	@Test
	public void testFindAllUserWithSuccess() {
		// Given
		// When
		List<UtilisateurBasicDTO> emps = this.userService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws EcolisBusinessException {
		// Given
		Integer userId = this.userIdForAllTest;
		// When
		UtilisateurFullDTO user = this.userService.findById(userId);
		// Then
		Assert.assertTrue(user.getId() == userId);
	}
	
	@Test
	public void testUpdateUser() throws AccessDeniedException, EcolisBusinessException {
		// Given
		UtilisateurFullDTO user = this.userService.findById(this.userIdForAllTest);
		user.setPhone("06854263985");
		// When
		this.userService.update(user);
		UtilisateurFullDTO clientUpdate = this.userService.findById(this.userIdForAllTest);
		// Then

		Assert.assertTrue(clientUpdate.getPhone() == "06854263985");
	}
	
	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer userId = this.userIdForAllTest;
		this.userIdForAllTest = null;
		// Whens
		this.userService.deleteById(userId);
		UtilisateurFullDTO user = this.userService.findById(userId);

		// Then
		Assert.assertNull(user);

	}
	
	@Test
	public void testGetUserByEmail() throws EcolisBusinessException{
		 //Recuperation par email
        Utilisateur utilisateur = userService.findByEmail("emmanuel.macron@gouv.fr");
        System.out.println("User authentifié :" + utilisateur);
        Assert.assertTrue(utilisateur != null);
	}
	
	@Before
	public void prepareAllEntityBefore() throws EcolisBusinessException {
		bcryptEncoder = new BCryptPasswordEncoder();
		UtilisateurFullDTO user = new UtilisateurFullDTO();
		user.setName("Abdou Zoyim Loti");
		user.setCivility("1");
		user.setEmail("abdou.zoyim@indyli-services.com");
		user.setEnabled((byte) 1);
		user.setLastConnection(new Date());
		user.setYearOfBirth(1992);
		user.setPhone("0398521647");
		user.setRegistrationDate(new Date());
		user.setLogin("loti");
		String encryptPassword = bcryptEncoder.encode("1234");
		user.setPassword(encryptPassword);
		
		user = userService.create(user);
		this.userIdForAllTest = user.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, EcolisBusinessException {
		if (this.userIdForAllTest != null) {
			this.userService.deleteById(this.userIdForAllTest);
		}

		if (!Objects.isNull(this.createUserId)) {
			this.userService.deleteById(this.createUserId);
		}
	}
}