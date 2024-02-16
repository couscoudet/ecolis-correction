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

import eu.fr.indyli.formation.business.dao.IAlerteDAO;
import eu.fr.indyli.formation.business.dao.IUtilisateurDAO;
import eu.fr.indyli.formation.business.entity.Alerte;
import eu.fr.indyli.formation.business.entity.Utilisateur;
import eu.fr.indyli.formation.business.utils.DateUtils;

@SpringBootApplication
@EntityScan("eu.fr.indyli.formation.business.entity")
@EnableJpaRepositories("eu.fr.indyli.formation.business.dao")
public class Application implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    IAlerteDAO alerteRepository;
    
    @Autowired
    IUtilisateurDAO userRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        System.out.println("\n1.findAll()...");
        for (Alerte alerteTmp : alerteRepository.findAll()) {
            System.out.println(alerteTmp);
        }
        //Recuperation par ville de depart
        System.out.println("Recherche par ville de depart");
        List<Alerte> listeVilleDep = this.alerteRepository.findByVilleDepart("Ndjamena, Tchad");
        for (Alerte alerteTmp : listeVilleDep) {
            System.out.println(alerteTmp);
        }
        
        //Recuperation par email et password
        Utilisateur user = userRepository.findByLoginAndPassword("emmanuel.macron@gouv.fr", "macron");
        System.out.println("User authentifié :" + user);
        //Recuperation Utilisateur ayant posté commentaire et annonces 
        System.out.println("Utilisateur ayant posté des commentaires et des annonces**************");
        Date datePivot = DateUtils.stringToDate("04/07/2015");
		List<Utilisateur> liste = this.userRepository.findAuthorsCommentByDateAndPostedAnnonce(datePivot, "Douala");
		for (Utilisateur utilisateur : liste) {
			System.out.println(utilisateur);
		}
		
		System.out.println("**************************Done!*******************************************");
        System.exit(0);
    }

}