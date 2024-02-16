package eu.fr.indyli.formation.business.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EntityScan("eu.fr.indyli.formation.business.entity")
//@ComponentScan(basePackages="eu.fr.indyli.formation.business.ecolis.service")
//@EnableJpaRepositories("eu.fr.indyli.formation.business.dao")
//@Import({EcolisDbConfig.class,TacheDbConfig.class})
@Import({EcolisDbConfig.class})
public class EcolisBusinessConfig {
}
