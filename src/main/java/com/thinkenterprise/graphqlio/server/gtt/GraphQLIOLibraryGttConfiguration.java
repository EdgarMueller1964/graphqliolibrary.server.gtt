package com.thinkenterprise.graphqlio.server.gtt;



import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = GraphQLIOLibraryGttConfiguration.class)
public class GraphQLIOLibraryGttConfiguration {

	
	  private static final Logger logger = LoggerFactory.getLogger(GraphQLIOLibraryGttConfiguration.class);

	  @PostConstruct
	  public void postConstruct(){
	    logger.info("GraphQLIOLIbrary GTT Module Loaded!");
	  }
}

