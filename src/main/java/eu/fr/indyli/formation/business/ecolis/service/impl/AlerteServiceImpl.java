package eu.fr.indyli.formation.business.ecolis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IAlerteDAO;
import eu.fr.indyli.formation.business.dto.AlerteBasicDTO;
import eu.fr.indyli.formation.business.dto.AlerteFullDTO;
import eu.fr.indyli.formation.business.ecolis.service.IAlerteService;
import eu.fr.indyli.formation.business.entity.Alerte;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;

@Service("alerteService")
public class AlerteServiceImpl extends AbstractServiceImpl<Alerte, AlerteBasicDTO, AlerteFullDTO, IAlerteDAO> implements IAlerteService{
	
	@Resource(name = EcolisConstantesDAO.ALERTE_DAO_KEY)
	private IAlerteDAO alerteDao;
	
	
	public AlerteServiceImpl() {
		super(Alerte.class, AlerteBasicDTO.class, AlerteFullDTO.class);
	}
	
	@Override
	public IAlerteDAO getDAO() {
		return this.alerteDao;
	}

}
