package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.entity.Alerte;

/**
 * 
 * @author CHZOME
 *
 */
public interface IAlerteService {

	public List<Alerte> findAll();
	
	/**
	 * Cree une alerte
	 * @param alerte
	 * @return
	 * @throws EcolisBusinessException
	 */
	public Integer createUpdateEntity(Alerte alerte) throws EcolisBusinessException;
	/**
	 * Recupère une alerte par son identifiant
	 * @param idAlerte : Identifiant de l'alerte à recuperer
	 * @return
	 * @throws EcolisBusinessException
	 */
	public Alerte findById(Integer idAlerte) throws EcolisBusinessException;
	
	/**
	 * Supprime une alerte par son identifiant
	 * @param idAlerte : Identifiant de l'alerte qu'on souhaite supprimer
	 * @throws EcolisBusinessException
	 */
	public void deleteAlerteById(Integer idAlerte) throws EcolisBusinessException;
}
