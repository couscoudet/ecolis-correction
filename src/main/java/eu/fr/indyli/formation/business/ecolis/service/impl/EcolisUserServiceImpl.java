package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEcolisUserDAO;
import eu.fr.indyli.formation.business.dto.EcolisUserBasicDTO;
import eu.fr.indyli.formation.business.dto.EcolisUserFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisUserService;
import eu.fr.indyli.formation.business.entity.EcolisUser;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import jakarta.annotation.Resource;

@Service(EcolisConstantesService.USER_SERVICE_KEY)
public class EcolisUserServiceImpl
		extends AbstractServiceImpl<EcolisUser, EcolisUserBasicDTO, EcolisUserFullDTO, IEcolisUserDAO>
		implements IEcolisUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Resource(name = EcolisConstantesDAO.USER_DAO_KEY)
	private IEcolisUserDAO userDAO;

	public EcolisUserServiceImpl() {
		super(EcolisUser.class, EcolisUserBasicDTO.class, EcolisUserFullDTO.class);
		this.bcryptEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public IEcolisUserDAO getDAO() {
		return this.userDAO;
	}

	@Override
	public EcolisUserBasicDTO findByLoginAndPassword(String email, String password) throws EcolisBusinessException {
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
		}
		EcolisUser foundUser = userDAO.findByLoginAndPassword(email, password);
		return this.getModelMapper().map(foundUser, EcolisUserBasicDTO.class);
	}

	@Override
	public List<EcolisUserBasicDTO> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot, String paramVilleArrivee)
			throws EcolisBusinessException {
		if (StringUtils.isBlank(paramVilleArrivee) || paramDatePivot == null) {
			throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
		}
		
		List<EcolisUser> users = userDAO.findAuthorsCommentByDateAndPostedAnnonce(paramDatePivot, paramVilleArrivee);
		return users.stream()
                .map(user -> this.getModelMapper().map(user, EcolisUserBasicDTO.class))
                .collect(Collectors.toList());
	}

	@Override
	public EcolisUserBasicDTO findByEmail(String email) throws EcolisBusinessException {
		if (StringUtils.isBlank(email))
			throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER L'EMAIL");
		EcolisUser user =  this.userDAO.findByEmail(email);
		return this.getModelMapper().map(user, EcolisUserBasicDTO.class);
	}

	/*
	 * @Override public Integer createUpdateUser(Utilisateur user) throws
	 * EcolisBusinessException { if (user.getIdUtilisateur() != null) { Utilisateur
	 * userInBase = this.userDAO.getOne(user.getIdUtilisateur());
	 * userInBase.setName(user.getName()); userInBase.setLogin(user.getLogin());
	 * userInBase.setEmail(user.getEmail());
	 * userInBase.setTelephone(user.getTelephone());
	 * userInBase.setAnneeDeNaissance(user.getAnneeDeNaissance()); user =
	 * userInBase; } // On verifie si l'email et le login n'existent pas déjà en
	 * base List<Utilisateur> listeUserExistantEnBaseAvecLoginOuEmail =
	 * this.userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail()); if
	 * (!listeUserExistantEnBaseAvecLoginOuEmail.isEmpty()) { throw new
	 * EcolisBusinessException("Le login :" + user.getLogin() + " Ou L'email :" +
	 * user.getEmail() + " semblent déjà pris"); } String encryptPassword =
	 * bcryptEncoder.encode(user.getLogin()); user.setPassword(encryptPassword);
	 * user = this.userDAO.saveAndFlush(user); return user.getIdUtilisateur(); }
	 */

	@Override
	public EcolisUserBasicDTO findByLogin(String login) throws EcolisBusinessException {
		EcolisUser user =  this.userDAO.findByLogin(login);
		return this.getModelMapper().map(user, EcolisUserBasicDTO.class);
	}

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		EcolisUser ecolisUser = userDAO.findByLogin(login);
		if (ecolisUser == null) {
			throw new UsernameNotFoundException("Invalid Login or Password.");
		}
		return new org.springframework.security.core.userdetails.User(ecolisUser.getLogin(), ecolisUser.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		// ROLE_ANONYMOUS
	}

	@Override
	public List<EcolisUserBasicDTO> findByLoginOrEmail(String login, String email) throws EcolisBusinessException {
		List<EcolisUser> users =  this.userDAO.findByLoginOrEmail(login, email);
		return users.stream()
                .map(user -> this.getModelMapper().map(user, EcolisUserBasicDTO.class))
                .collect(Collectors.toList());
	}

	@Override
	public EcolisUserFullDTO create(EcolisUserFullDTO user) throws EcolisBusinessException {
		// On verifie si l'email et le login n'existent pas déjà en base
		List<EcolisUser> listeUserExistantEnBaseAvecLoginOuEmail = this.userDAO.findByLoginOrEmail(user.getLogin(),
				user.getEmail());
		if (!listeUserExistantEnBaseAvecLoginOuEmail.isEmpty()) {
			throw new EcolisBusinessException(
					"Le login :" + user.getLogin() + " Ou L'email :" + user.getEmail() + " semblent déjà pris");
		}
		EcolisUser userEntity = this.getModelMapper().map(user, EcolisUser.class);
		String encryptPassword = bcryptEncoder.encode(user.getPassword());
		userEntity.setPassword(encryptPassword);
		EcolisUser savedUser = this.userDAO.save(userEntity);
		return this.getModelMapper().map(savedUser, EcolisUserFullDTO.class);
	}

	@Override
	public EcolisUserFullDTO update(EcolisUserFullDTO user) throws EcolisBusinessException {
		Optional<EcolisUser> existingUserOptional = this.userDAO.findById(user.getId());
		if (existingUserOptional.isPresent()) {
			EcolisUser existingUser = existingUserOptional.get();
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				existingUser.setCivility(user.getCivility());
				existingUser.setName(user.getName());
				existingUser.setLogin(user.getLogin());
				existingUser.setEmail(user.getEmail());
				existingUser.setEnabled(user.getEnabled());
				existingUser.setLastConnection(user.getLastConnection());
				existingUser.setYearOfBirth(user.getYearOfBirth());
				existingUser.setPhone(user.getPhone());
				existingUser.setRegistrationDate(user.getRegistrationDate());
				existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
				EcolisUser updatedUser = this.userDAO.saveAndFlush(existingUser);
				return this.getModelMapper().map(updatedUser, EcolisUserFullDTO.class);
			} else {
				existingUser.setCivility(user.getCivility());
				existingUser.setName(user.getName());
				existingUser.setLogin(user.getLogin());
				existingUser.setEmail(user.getEmail());
				existingUser.setEnabled(user.getEnabled());
				existingUser.setLastConnection(user.getLastConnection());
				existingUser.setYearOfBirth(user.getYearOfBirth());
				existingUser.setPhone(user.getPhone());
				existingUser.setRegistrationDate(user.getRegistrationDate());
				EcolisUser updatedUser =  this.userDAO.saveAndFlush(existingUser);
				return this.getModelMapper().map(updatedUser, EcolisUserFullDTO.class);
			}
		} else {
			throw new EcolisBusinessException("Utilisateur introuvable avec l'ID : " + user.getId());
		}
	}

}
