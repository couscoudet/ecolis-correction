package eu.fr.indyli.formation.business.ecolis.service;

import eu.fr.indyli.formation.business.dao.IAlerteDAO;
import eu.fr.indyli.formation.business.dto.AlerteBasicDTO;
import eu.fr.indyli.formation.business.dto.AlerteFullDTO;
import eu.fr.indyli.formation.business.entity.Alerte;

/**
 * 
 * @author CHZOME
 *
 */
public interface IAlerteService extends IAbstractServices<Alerte, AlerteBasicDTO, AlerteFullDTO, IAlerteDAO> {
	
}
