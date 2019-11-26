package com.thinkenterprise.graphqlio.server.gtt.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "graphqlio.toolstypes")
public class GttProperties {

}
