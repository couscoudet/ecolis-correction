package eu.fr.indyli.formation.business.application;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import eu.fr.indyli.formation.business.dao.IEcolisAlertDAO;
import eu.fr.indyli.formation.business.dao.IEcolisUserDAO;
import eu.fr.indyli.formation.business.entity.EcolisAlert;
import eu.fr.indyli.formation.business.entity.EcolisUser;
import eu.fr.indyli.formation.business.utils.DateUtils;

@SpringBootApplication
@EntityScan("eu.fr.indyli.formation.business.entity")
@EnableJpaRepositories("eu.fr.indyli.formation.business.dao")
public class Application implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    IEcolisAlertDAO alerteRepository;
    
    @Autowired
    IEcolisUserDAO userRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        System.out.println("\n1.findAll()...");
        for (EcolisAlert alerteTmp : alerteRepository.findAll()) {
            System.out.println(alerteTmp);
        }
        //Recuperation par ville de depart
        System.out.println("Recherche par ville de depart");
        List<EcolisAlert> listeVilleDep = this.alerteRepository.findByStartCity("Ndjamena, Tchad");
        for (EcolisAlert alerteTmp : listeVilleDep) {
            System.out.println(alerteTmp);
        }
        
        //Recuperation par email et password
        EcolisUser ecolisUser = userRepository.findByLoginAndPassword("emmanuel.macron@gouv.fr", "macron");
        System.out.println("User authentifié :" + ecolisUser);
        //Recuperation Utilisateur ayant posté commentaire et annonces 
        System.out.println("Utilisateur ayant posté des commentaires et des annonces**************");
        Date datePivot = DateUtils.stringToDate("04/07/2015");
		List<EcolisUser> liste = this.userRepository.findAuthorsCommentByDateAndPostedAnnonce(datePivot, "Douala");
		for (EcolisUser userItem : liste) {
			System.out.println(userItem);
		}
		
		System.out.println("**************************Done!*******************************************");
        System.exit(0);
    }

}