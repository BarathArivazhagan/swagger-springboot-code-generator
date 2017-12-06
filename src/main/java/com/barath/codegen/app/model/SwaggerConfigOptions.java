package com.barath.codegen.app.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("options")
public class SwaggerConfigOptions {
	
	private String modelPropertyNaming;
	
	private String apiPackage;
	
	private String modelPackage;

	public String getModelPropertyNaming() {
		return modelPropertyNaming;
	}

	public void setModelPropertyNaming(String modelPropertyNaming) {
		this.modelPropertyNaming = modelPropertyNaming;
	}

	public String getApiPackage() {
		return apiPackage;
	}

	public void setApiPackage(String apiPackage) {
		this.apiPackage = apiPackage;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public SwaggerConfigOptions(String modelPropertyNaming, String apiPackage, String modelPackage) {
		super();
		this.modelPropertyNaming = modelPropertyNaming;
		this.apiPackage = apiPackage;
		this.modelPackage = modelPackage;
	}

	public SwaggerConfigOptions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
