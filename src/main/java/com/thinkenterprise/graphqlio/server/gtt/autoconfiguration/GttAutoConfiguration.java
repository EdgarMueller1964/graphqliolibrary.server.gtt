package com.thinkenterprise.graphqlio.server.gtt.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thinkenterprise.graphqlio.server.gtt.types.GttDateType;
import com.thinkenterprise.graphqlio.server.gtt.types.GttJsonType;
import com.thinkenterprise.graphqlio.server.gtt.types.GttUuidType;
import com.thinkenterprise.graphqlio.server.gtt.types.GttVoidType;

@Configuration
@EnableConfigurationProperties(GttAutoConfiguration.class)
@ConfigurationProperties(prefix = "graphqlio.toolstypes")
public class GttAutoConfiguration{

	@Autowired
	private GttProperties gttProperties;
    	
	@Bean 
	public GttUuidType gttUuidType() { return new GttUuidType(); }
	
	@Bean 
	public GttDateType gttDateType() { return new GttDateType(); }

	@Bean 
	public GttJsonType gttJsonType() { return new GttJsonType(); }

	@Bean 
	public GttVoidType gttVoidType() { return new GttVoidType(); }
	
}