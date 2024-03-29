package com.dev.gateway;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
	private static final Logger logger = LogManager.getLogger(ApiGatewayApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

    @Bean
    @Lazy(false)
    List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        for (RouteDefinition definition : definitions) {
            logger.info("id: " + definition.getId() + "  " + definition.getUri().toString());
        }
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-services")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-services", "");
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
        });
        return groups;
    }


}
