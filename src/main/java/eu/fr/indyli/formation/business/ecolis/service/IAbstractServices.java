package eu.fr.indyli.formation.business.ecolis.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public interface IAbstractServices <Entity, BasicDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>> {
	
	/**
	 * Create an new entity
	 * 
	 * @param ent : POJO to create
	 * @return
	 * @throws EcolisBusinessException
	 */
	public FullDTO create(FullDTO ent) throws EcolisBusinessException;
	
	/**
	 * Update an entity
	 * 
	 * @param entToUpdate
	 * @throws EcolisBusinessException,AccessDeniedException
	 */
	public FullDTO update(FullDTO eOntToUpdate) throws EcolisBusinessException, AccessDeniedException;
	
	/**
	 * Delete an entity
	 * 
	 * @param id : Entity'Id to delete
	 * @throws EcolisBusinessException
	 * @throws AccessDeniedException
	 * 
	 */
	public void deleteById(int id) throws EcolisBusinessException, AccessDeniedException;
	
	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<BasicDTO> findAll();
	
	
	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<FullDTO> findAllFull();
	
	
	/**
	 * Find All Entity Pageable
	 * 
	 * @return
	 */
	public Page<FullDTO> findAllPageable(Pageable pageable);
	
	
	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws EcolisBusinessException
	 */

	public FullDTO findById(int id) throws EcolisBusinessException;
	
	
	/**
	 * Teste l'existence d'une EntitÃ© par son id
	 * 
	 * @param id
	 * @return
	 * @throws EcolisBusinessException
	 */
	public boolean ifEntityExistById(int id) throws EcolisBusinessException;
	
	
	/**
	 * Recupere un DTO par son id
	 */
	public <T extends BasicDTO> T findById(int id, Class<T> type) throws EcolisBusinessException;
	
	
	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	public IEntityDAO getDAO();

}
