package eu.fr.indyli.formation.business.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tacheEntityManagerFactory",
						transactionManagerRef = "tacheTransactionManager", 
						basePackages = {"eu.fr.indyli.formation.business.taches.dao"})
@EntityScan("eu.fr.indyli.formation.business.taches.entity")
public class TacheDbConfig {

	@Bean(name = "tacheDatasource")
	@ConfigurationProperties(prefix = "tache.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "tacheEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean 
	entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("tacheDatasource") DataSource dataSource){
		return builder
				.dataSource(dataSource)
				.packages("eu.fr.indyli.formation.business.taches.entity")
				.persistenceUnit("tache-unit-db")
				.build();
	}

	@Bean(name = "tacheTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("tacheEntityManagerFactory") EntityManagerFactory 
			entityManagerFactory
			) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
