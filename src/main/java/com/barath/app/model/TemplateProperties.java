package com.barath.app.model;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(ignoreUnknownFields=true,prefix="codegen.template")
public class TemplateProperties {
	
	private List<String> templates;

}
