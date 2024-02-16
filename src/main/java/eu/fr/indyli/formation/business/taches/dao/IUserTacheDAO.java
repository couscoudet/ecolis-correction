package eu.fr.indyli.formation.business.taches.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.business.taches.entity.UserTache;
import eu.fr.indyli.formation.business.utils.EcolisConstantes;

@Repository(EcolisConstantes.EcolisConstantesDAO.USER_TACHE_DAO_KEY)
public interface IUserTacheDAO extends JpaRepository<UserTache, Integer>{

}
