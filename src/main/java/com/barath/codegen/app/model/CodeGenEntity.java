package com.barath.codegen.app.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CodeGenEntity {


    
	private Map<String,CodeGenEntityProperty> properties;


	@Override
	public String toString() {
		return "CodeGenEntity{" +
				"properties=" + properties +
				'}';
	}

	public void setProperties(Map<String, CodeGenEntityProperty> properties) {
		this.properties = properties;
	}

	public Map<String, CodeGenEntityProperty> getProperties() {

		return properties;
	}
}
