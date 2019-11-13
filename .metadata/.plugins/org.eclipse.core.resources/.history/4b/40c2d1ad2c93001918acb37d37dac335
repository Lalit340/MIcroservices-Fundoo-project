package com.bridgelabz.apigateway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Controller
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		 List<SwaggerResource> resources = new ArrayList<>();
	        resources.add(swaggerResource("userservice", "/user/v2/api-docs", "2.0"));
	        resources.add(swaggerResource("noteservice", "/note/v2/api-docs", "2.0"));
	        return resources;
	}
	
	 private SwaggerResource swaggerResource(String name, String location, String version) {
	        SwaggerResource swaggerResource = new SwaggerResource();
	        swaggerResource.setName(name);
	        swaggerResource.setLocation(location);
	        swaggerResource.setSwaggerVersion(version);
	        return swaggerResource;
	    }

}
