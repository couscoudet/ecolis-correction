package eu.fr.indyli.formation.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.EcolisAdvertising;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

@Repository(EcolisConstantes.EcolisConstantesDAO.ANNONCE_DAO_KEY)
public interface IEcolisAdvertisingDAO extends JpaRepository<EcolisAdvertising, Integer>{



	@Modifying
	@Query("update EcolisAdvertising an set an.prime = an.prime * 2  where an.startCity like CONCAT('%',:paramDepTown,'%')")
	public void setPrimeForSomeDeparture(@Param("paramDepTown") String departureTown) throws EcolisBusinessException;
	
	/**
	 * Remonte les annonces dont la ville de depart contient le parametre passe
	 * @param departureTown
	 * @return
	 */
	@Query("Select an From EcolisAdvertising an where an.startCity like CONCAT('%',:paramDepTown,'%')")
	public List<EcolisAdvertising> findByVilleDepartContaining(@Param("paramDepTown")  String departureTown);
}
