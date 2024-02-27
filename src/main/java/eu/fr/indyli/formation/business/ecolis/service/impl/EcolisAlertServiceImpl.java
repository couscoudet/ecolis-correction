package eu.fr.indyli.formation.business.ecolis.service.impl;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEcolisAlertDAO;
import eu.fr.indyli.formation.business.dto.EcolisAlertBasicDTO;
import eu.fr.indyli.formation.business.dto.EcolisAlertFullDTO;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisAlertService;
import eu.fr.indyli.formation.business.entity.EcolisAlert;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import jakarta.annotation.Resource;

@Service("alerteService")
public class EcolisAlertServiceImpl extends AbstractServiceImpl<EcolisAlert, EcolisAlertBasicDTO, EcolisAlertFullDTO, IEcolisAlertDAO> implements IEcolisAlertService{
	
	@Resource(name = EcolisConstantesDAO.ALERTE_DAO_KEY)
	private IEcolisAlertDAO alerteDao;
	
	
	public EcolisAlertServiceImpl() {
		super(EcolisAlert.class, EcolisAlertBasicDTO.class, EcolisAlertFullDTO.class);
	}
	
	@Override
	public IEcolisAlertDAO getDAO() {
		return this.alerteDao;
	}

}
