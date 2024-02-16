package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.dto.UtilisateurBasicDTO;
import eu.fr.indyli.formation.business.dto.UtilisateurFullDTO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IUtilisateurService;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.USER_SERVICE_KEY)
public class UtilisateurServiceImpl extends AbstractServiceImpl<Utilisateur, UtilisateurBasicDTO, UtilisateurFullDTO, IUtilisateurDAO> implements IUtilisateurService, UserDetailsService {

	// @Autowired
	   private BCryptPasswordEncoder bcryptEncoder;
	   
	   @Resource(name = EcolisConstantesDAO.USER_DAO_KEY) 
		private IUtilisateurDAO userDAO;
	  
	  public UtilisateurServiceImpl() {
		super(Utilisateur.class, UtilisateurBasicDTO.class, UtilisateurFullDTO.class);
		this.bcryptEncoder = new BCryptPasswordEncoder();
	}
	  
    @Override
	public IUtilisateurDAO getDAO() {
		return this.userDAO;
	}
	
	  @Override
	  public Utilisateur findByLoginAndPassword(String email, String password)
	      throws EcolisBusinessException {
	    if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
	      throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
	    }
	    Utilisateur foundUser = userDAO.findByLoginAndPassword(email, password);
	    return foundUser;
	  }
	
	  @Override
	  public List<Utilisateur> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot,
	      String paramVilleArrivee) throws EcolisBusinessException {
	    if (StringUtils.isBlank(paramVilleArrivee) || paramDatePivot == null) {
	      throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
	    }
	    return userDAO.findAuthorsCommentByDateAndPostedAnnonce(paramDatePivot, paramVilleArrivee);
	  }
	
	  @Override
	  public Utilisateur findByEmail(String email) throws EcolisBusinessException {
	    if (StringUtils.isBlank(email))
	      throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER L'EMAIL");
	    return this.userDAO.findByEmail(email);
	  }
	
	  /*@Override
	  public Integer createUpdateUser(Utilisateur user) throws EcolisBusinessException {
	    if (user.getIdUtilisateur() != null) {
	      Utilisateur userInBase = this.userDAO.getOne(user.getIdUtilisateur());
	      userInBase.setName(user.getName());
	      userInBase.setLogin(user.getLogin());
	      userInBase.setEmail(user.getEmail());
	      userInBase.setTelephone(user.getTelephone());
	      userInBase.setAnneeDeNaissance(user.getAnneeDeNaissance());
	      user = userInBase;
	    }
	    // On verifie si l'email et le login n'existent pas déjà en base
	    List<Utilisateur> listeUserExistantEnBaseAvecLoginOuEmail =
	        this.userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail());
	    if (!listeUserExistantEnBaseAvecLoginOuEmail.isEmpty()) {
	      throw new EcolisBusinessException("Le login :" + user.getLogin() + " Ou L'email :"
	          + user.getEmail() + " semblent déjà pris");
	    }
	    String encryptPassword = bcryptEncoder.encode(user.getLogin());
	    user.setPassword(encryptPassword);
	    user = this.userDAO.saveAndFlush(user);
	    return user.getIdUtilisateur();
	  }*/
	
	  @Override
	  public Utilisateur findByLogin(String login) throws EcolisBusinessException {
	    return this.userDAO.findByLogin(login);
	  }
	
	  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	    Utilisateur utilisateur = userDAO.findByLogin(login);
	    if (utilisateur == null) {
	      throw new UsernameNotFoundException("Invalid Login or Password.");
	    }
	    return new org.springframework.security.core.userdetails.User(utilisateur.getLogin(),
	        utilisateur.getPassword(), getAuthority());
	  }
	
	  private List<SimpleGrantedAuthority> getAuthority() {
	    return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
	        new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
	    // ROLE_ANONYMOUS
	  }
	
	  @Override
	  public List<Utilisateur> findByLoginOrEmail(String login, String email)
	      throws EcolisBusinessException {
	    return this.userDAO.findByLoginOrEmail(login, email);
	  }

	@Override
	public Utilisateur createUser(Utilisateur user) throws EcolisBusinessException {
		String encryptPassword = bcryptEncoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		return this.userDAO.save(user);
	}

	@Override
	public Utilisateur updateUser(Utilisateur user) throws EcolisBusinessException {
		
		Optional<Utilisateur> existingUserOptional  = this.userDAO.findById(user.getId());
		
	    if (existingUserOptional.isPresent()) {
	    	Utilisateur existingUser = existingUserOptional.get();
	    	
	    	System.out.println("Password............................ " +user.getPassword());
	    	
	    	if(user.getPassword() != null && !user.getPassword().isEmpty()) {
	    		
			    existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			    
			    Utilisateur updatedUser = this.userDAO.saveAndFlush(existingUser);
			    return updatedUser;
			    
	    	}else {
	    		
	    		existingUser.setCivility(user.getCivility());
	    		existingUser.setName(user.getName());
	    		existingUser.setLogin(user.getLogin());
	    		existingUser.setEmail(user.getEmail());
	    		existingUser.setEnabled((byte) user.getEnabled());
	    		existingUser.setLastConnection(user.getLastConnection());
	    		existingUser.setYearOfBirth(user.getYearOfBirth());
	    		existingUser.setPhone(user.getPhone());
	    		existingUser.setRegistrationDate(user.getRegistrationDate());
	    		
	    		return this.userDAO.saveAndFlush(existingUser); 
	    	} 
	    	
	    }else {
	    	throw new EcolisBusinessException("Utilisateur introuvable avec l'ID : " + user.getId());
	    }
	}

}
