package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.fr.indyli.formation.business.dto.IDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAbstractServices;
import eu.fr.indyli.formation.business.entity.IEntity;
import jakarta.annotation.Resource;

public abstract class AbstractServiceImpl<Entity extends IEntity, BasicDTO extends IDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>> 
implements IAbstractServices<Entity, BasicDTO, FullDTO, IEntityDAO> {
	
	private Class<Entity> entityClass;
	private Class<BasicDTO> viewClass;
	private Class<FullDTO> fullDTOClass;
	
	@Resource(name = "ecolis-modelmapper")
	private ModelMapper mapper;

	public AbstractServiceImpl(Class<Entity> entityClass, Class<BasicDTO> viewClass, Class<FullDTO> fullDTOClass) {
		super();
		this.entityClass = entityClass;
		this.viewClass = viewClass;
		this.fullDTOClass = fullDTOClass;
	}

	@Override
	public FullDTO create(FullDTO view) throws EcolisBusinessException {
		Entity entity = this.getModelMapper().map(view, this.entityClass);
		this.getDAO().save(entity);
		view.setId(entity.getId());
		return view;

	}

	@Override
	public FullDTO update(FullDTO viewToUpdate) throws EcolisBusinessException, AccessDeniedException {
		Entity entity = this.getModelMapper().map(viewToUpdate, this.entityClass);
		entity = this.getDAO().saveAndFlush(entity);
		return this.getModelMapper().map(entity, this.fullDTOClass);
	}

	@Override
	public void deleteById(int id) throws EcolisBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);
	}

	@Override
	public List<BasicDTO> findAll() {
		List<Entity> list = this.getDAO().findAll();
		List<BasicDTO> viewList = new ArrayList<BasicDTO>();
		for (Entity ent : list) {
			BasicDTO view = this.getModelMapper().map(ent, this.viewClass);
			viewList.add(view);
		}
		return viewList;

	}

	@Override
	public List<FullDTO> findAllFull() {
		List<Entity> list = this.getDAO().findAll();
		List<FullDTO> viewListFull = new ArrayList<FullDTO>();
		for (Entity ent : list) {
			FullDTO viewFull = this.getModelMapper().map(ent, this.fullDTOClass);
			viewListFull.add(viewFull);
		}
		return viewListFull;

	}

	@Override
	public Page<FullDTO> findAllPageable(Pageable pageable) {
		List<Entity> list = this.getDAO().findAll();
		List<FullDTO> viewList = new ArrayList<FullDTO>();
		for (Entity ent : list) {
			FullDTO view = this.getModelMapper().map(ent, this.fullDTOClass);
			viewList.add(view);
		}
		int pageSize = pageable.getPageSize();
	    int currentPage = pageable.getPageNumber();
	    int startItem = currentPage * pageSize;
	    List<FullDTO> pageList;
	    if (viewList.size() < startItem) {
	        pageList = Collections.emptyList();
	    } else {
	        int toIndex = Math.min(startItem + pageSize, viewList.size());
	        pageList = viewList.subList(startItem, toIndex);
	    }
	    
	    Page<FullDTO> fullDTOPage = new PageImpl<>(pageList, PageRequest.of(currentPage, pageSize), viewList.size());
		return fullDTOPage;

	}

	@Override
	public FullDTO findById(int id) throws EcolisBusinessException {
		Entity entity = this.getDAO().findById(id).orElse(null);
		if (entity != null) {

			FullDTO view = this.getModelMapper().map(entity, this.fullDTOClass);
			return view;
		} else {
			return null;
		}

	}

	@Override
	public boolean ifEntityExistById(int id) throws EcolisBusinessException {
		return this.getDAO().existsById(id);
	}

	@Override
	public <T extends BasicDTO> T findById(int id, Class<T> type) throws EcolisBusinessException {
		Entity ent = this.getDAO().getById(id);
		T view = this.getModelMapper().map(ent, type);
		return view;
	}
	
	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	
	public abstract IEntityDAO getDAO();
	
	public ModelMapper getModelMapper() {
		return this.mapper;
	}


}
