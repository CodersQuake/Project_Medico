package com.hms;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediConnect {

	public static void main(String[] args) {
		
	   SpringApplication.run(MediConnect.class, args) ;
	}
	
	@Bean
	public ModelMapper getModelMapper()
	{   
		ModelMapper mapper = new ModelMapper() ;
		
		mapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		
		.setPropertyCondition(Conditions.isNotNull()) ;
		
		
		return mapper ;
		
	}
}