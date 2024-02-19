package eu.fr.indyli.formation.business.ecolis.service;

import eu.fr.indyli.formation.business.dao.IEcolisAlertDAO;
import eu.fr.indyli.formation.business.dto.EcolisAlertBasicDTO;
import eu.fr.indyli.formation.business.dto.EcolisAlertFullDTO;
import eu.fr.indyli.formation.business.entity.EcolisAlert;

/**
 * 
 * @author CHZOME
 *
 */
public interface IEcolisAlertService extends IAbstractServices<EcolisAlert, EcolisAlertBasicDTO, EcolisAlertFullDTO, IEcolisAlertDAO> {
	
}
