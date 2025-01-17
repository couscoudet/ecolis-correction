package eu.fr.indyli.formation.business.entity;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name = "Message")
public class EcolisMessage implements IEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 5315428355602942960L;
  
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id_message", unique = true, nullable = false)
  private Integer messageId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_annonce", nullable = false)
  private EcolisAdvertising announcement;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_utilisateur")
  private EcolisUser user;
  
  @Column(name = "corps", length = 65535)
  private String corps;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_creation", nullable = false, length = 19)
  private Date creationDate;

  public EcolisMessage() {}

  public EcolisMessage(EcolisAdvertising announcement, Date creationDate) {
    this.announcement = announcement;
    this.creationDate = creationDate;
  }

  public EcolisMessage(EcolisAdvertising announcement, EcolisUser user, String corps, Date creationDate) {
    this.announcement = announcement;
    this.user = user;
    this.corps = corps;
    this.creationDate = creationDate;
  }

  public EcolisMessage(Integer messageId, String corps, Date creationDate) {
    super();
    this.messageId = messageId;
    this.corps = corps;
    this.creationDate = creationDate;
  }
  
  @Override
  public Integer getId() {
  	return this.messageId;
  }

  @Override
  public void setId(Integer id) {
  	this.messageId = id;
  }

  public EcolisAdvertising getAnnouncement() {
    return this.announcement;
  }

  public void setAnnouncement(EcolisAdvertising announcement) {
    this.announcement = announcement;
  }

  public EcolisUser getUser() {
    return this.user;
  }

  public void setUser(EcolisUser user) {
    this.user = user;
  }

  public String getCorps() {
    return this.corps;
  }

  public void setCorps(String corps) {
    this.corps = corps;
  }

  public Date getCreationDate() {
    return this.creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
