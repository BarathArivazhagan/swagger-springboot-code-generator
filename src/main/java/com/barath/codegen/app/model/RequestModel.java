package com.barath.codegen.app.model;


import org.springframework.web.multipart.MultipartFile;

import io.spring.initializr.generator.ProjectRequest;



public class RequestModel extends ProjectRequest{
	
	private MultipartFile swaggerJson;
	
	private String templateType;
	
	

	public MultipartFile getSwaggerJson() {
		return swaggerJson;
	}

	public void setSwaggerJson(MultipartFile swaggerJson) {
		this.swaggerJson = swaggerJson;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public RequestModel() {
		super();
		
	}

	


	
	
	
	
	

}
