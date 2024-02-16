package eu.fr.indyli.formation.business.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractEcolisEntity {

	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
