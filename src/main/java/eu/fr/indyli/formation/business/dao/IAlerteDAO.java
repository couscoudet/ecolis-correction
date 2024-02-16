package eu.fr.indyli.formation.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.business.entity.Alerte;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

@Repository(EcolisConstantes.EcolisConstantesDAO.ALERTE_DAO_KEY)
public interface IAlerteDAO extends JpaRepository<Alerte, Integer>{

	public List<Alerte> findByVilleDepart(String villeDep);
}
