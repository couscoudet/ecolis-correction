package eu.fr.indyli.formation.business.dto;

import java.util.Date;

public class UtilisateurBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2828766325754373536L;
	private Integer userId;
	private String civility;
	private String name;
	private String login;
	private String password;
	private String email;
	private byte enabled;
	private Date lastConnection;
	private int yearOfBirth;
	private String phone;
	private Date registrationDate;
	
	@Override
	public Integer getId() {
		return this.userId;
	}
	
	@Override
	public void setId(Integer id) {
		this.userId = id;
	}
	
	public String getCivility() {
		return civility;
	}
	
	public void setCivility(String civility) {
		this.civility = civility;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public byte getEnabled() {
		return enabled;
	}
	
	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}
	
	public Date getLastConnection() {
		return lastConnection;
	}
	
	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}
