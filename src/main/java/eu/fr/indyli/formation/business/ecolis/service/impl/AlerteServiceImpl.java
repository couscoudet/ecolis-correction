package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IAlerteDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAlerteService;
import eu.fr.indyli.formation.business.entity.Alerte;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service("alerteService")
public class AlerteServiceImpl implements IAlerteService{
	
	@Resource(name = "alerteDAO")
	private IAlerteDAO alerteDAO = null;
	
	
	@Override
	public List<Alerte> findAll() {
		List<Alerte> alertesList = alerteDAO.findAll();
		for (Alerte alerte : alertesList) {
			alerte.setUtilisateur(null);
		}
		return alertesList;
	}
	@Override
	public Integer createUpdateEntity(Alerte pAlerte) throws EcolisBusinessException {
		pAlerte = this.alerteDAO.saveAndFlush(pAlerte);
		return pAlerte.getIdAlerte();
	}
	@Override
	public Alerte findById(Integer idAlerte) throws EcolisBusinessException {
		return this.alerteDAO.getOne(idAlerte);
	}
	@Override
	public void deleteAlerteById(Integer idAlerte) throws EcolisBusinessException {
		this.alerteDAO.deleteById(idAlerte);
	}

}
