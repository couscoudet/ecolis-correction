package eu.fr.insee.formation.business.service.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
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
	
	
	@Test
	public void testGetUserByEmail() throws EcolisBusinessException{
		 //Recuperation par email
        Utilisateur user = userService.findByEmail("emmanuel.macron@gouv.fr");
        System.out.println("User authentifié :" + user);
        Assert.assertTrue(user != null);
	}
}
