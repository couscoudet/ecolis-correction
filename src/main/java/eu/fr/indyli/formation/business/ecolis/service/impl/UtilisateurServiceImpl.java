package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IUtilisateurService;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesDAO;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.USER_SERVICE_KEY)
public class UtilisateurServiceImpl implements IUtilisateurService, UserDetailsService {

  @Resource(name = EcolisConstantesDAO.USER_DAO_KEY)
  private IUtilisateurDAO userDAO = null;

  // @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  public UtilisateurServiceImpl() {
    bcryptEncoder = new BCryptPasswordEncoder();
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

  @Override
  public List<Utilisateur> findAll() {
    // TODO Auto-generated method stub
    List<Utilisateur> userList = this.userDAO.findAll();
    deleteAssociatedBean(userList);
    return userList;
  }


  private void deleteAssociatedBean(List<Utilisateur> userList) {
    for (Utilisateur utilisateur : userList) {
      utilisateur.setAnnonces(null);
      utilisateur.setComments(null);
      utilisateur.setImages(null);
      utilisateur.setMessages(null);
      utilisateur.setAlertes(null);
    }
  }

  @Override
  public Utilisateur findById(Integer id) throws EcolisBusinessException {
    Utilisateur utilisateur =
        this.userDAO.findById(id)
            .orElseThrow(() -> new EcolisBusinessException("Utilisateur non existant pour cet Id"));
    utilisateur.setAnnonces(null);
    utilisateur.setComments(null);
    utilisateur.setImages(null);
    utilisateur.setMessages(null);
    utilisateur.setAlertes(null);
    return utilisateur;
  }

  @Override
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
  }

  @Override
  public Utilisateur findByLogin(String login) throws EcolisBusinessException {
    return this.userDAO.findByLogin(login);
  }

  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Utilisateur user = userDAO.findByLogin(login);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid Login or Password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getLogin(),
        user.getPassword(), getAuthority());
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
        new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    // ROLE_ANONYMOUS
  }

  @Override
  public void deleteUserById(Integer id) throws EcolisBusinessException {
    this.userDAO.deleteById(id);
  }

  @Override
  public List<Utilisateur> findByLoginOrEmail(String login, String email)
      throws EcolisBusinessException {
    return this.userDAO.findByLoginOrEmail(login, email);
  }

}
