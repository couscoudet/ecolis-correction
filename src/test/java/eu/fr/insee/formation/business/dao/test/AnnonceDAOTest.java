package eu.fr.insee.formation.business.dao.test;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dao.IAnnonceDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Annonce;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AnnonceDAOTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource(name = EcolisConstantesDAO.ANNONCE_DAO_KEY)
	private IAnnonceDAO annonceRepository;


	@Test
	public void testFindAllWithSuccess() throws EcolisBusinessException {
		List<Annonce> aList = annonceRepository.findAll();
		Assert.assertTrue(!CollectionUtils.isEmpty(aList));
	}

	@Test
	public void testFindAnnonceWithDepartTownWithSuccess() {
		//Given
		String departureTown = "Paris";
		
		//When
		List<Annonce> aList = annonceRepository.findByVilleDepartContaining(departureTown);
		
		//Then
		Assert.assertTrue(!CollectionUtils.isEmpty(aList));
	}

	@Test
	public void testUpdateDoublePrimeAnnonceWithSuccess() throws EcolisBusinessException {
		//Given
		String departureTown = "Paris";
		List<Annonce> aListBefore = annonceRepository.findByVilleDepartContaining(departureTown);
		
		//When
		annonceRepository.setPrimeForSomeDeparture(departureTown);
		entityManager.clear();
		
		//Then
		List<Annonce> aListAfter = annonceRepository.findByVilleDepartContaining(departureTown);
		for (Annonce anAfter : aListAfter) {
			for (Annonce anBefore : aListBefore) {
				if (anAfter.getId().equals(anBefore.getId())) {
					Assert.assertTrue(anAfter.getPrime() == anBefore.getPrime() * 2);
				}
			}
		}
	}
}
