package com.thinkenterprise.graphqlio.server.gtt;


import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(GraphQLIOLibraryGttConfiguration.class)
@Configuration
public @interface EnableGraphQLIOGttLibraryModule {
}