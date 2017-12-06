package com.barath.codegen.app.model;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;




@Configuration
@ConfigurationProperties(ignoreUnknownFields=true,prefix="codegen.template")
public class TemplateProperties {
	
	private List<String> templates;

	public List<String> getTemplates() {
		return templates;
	}

	public void setTemplates(List<String> templates) {
		this.templates = templates;
	}
	
	

}
