package eu.fr.indyli.formation.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@EntityScan("eu.fr.indyli.formation.business.entity")
@Import({EcolisDbConfig.class})
public class EcolisBusinessConfig {
	
	@Bean(value = "ecolis-modelmapper")
	@Scope(value = "singleton")
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
