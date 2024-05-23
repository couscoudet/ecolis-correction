package eu.fr.insee.formation.business.dao.test;

import java.util.List;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.business.dao.IEcolisAdvertisingDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.EcolisAdvertising;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EcolisBusinessConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("postgres")
public class AnnonceDAOTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource(name = EcolisConstantesDAO.ANNONCE_DAO_KEY)
	private IEcolisAdvertisingDAO annonceRepository;


	@Test
	public void testFindAllWithSuccess() throws EcolisBusinessException {
		List<EcolisAdvertising> aList = annonceRepository.findAll();
		Assert.assertTrue(!CollectionUtils.isEmpty(aList));
	}

	@Test
	public void testFindAnnonceWithDepartTownWithSuccess() {
		//Given
		String departureTown = "Paris";
		
		//When
		List<EcolisAdvertising> aList = annonceRepository.findByVilleDepartContaining(departureTown);
		
		//Then
		Assert.assertTrue(!CollectionUtils.isEmpty(aList));
	}

	@Test
	public void testUpdateDoublePrimeAnnonceWithSuccess() throws EcolisBusinessException {
		//Given
		String departureTown = "Paris";
		List<EcolisAdvertising> aListBefore = annonceRepository.findByVilleDepartContaining(departureTown);
		
		//When
		annonceRepository.setPrimeForSomeDeparture(departureTown);
		entityManager.clear();
		
		//Then
		List<EcolisAdvertising> aListAfter = annonceRepository.findByVilleDepartContaining(departureTown);
		for (EcolisAdvertising anAfter : aListAfter) {
			for (EcolisAdvertising anBefore : aListBefore) {
				if (anAfter.getId().equals(anBefore.getId())) {
					Assert.assertTrue(anAfter.getPrime() == anBefore.getPrime() * 2);
				}
			}
		}
	}
}
