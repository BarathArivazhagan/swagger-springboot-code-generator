package com.barath.codegen.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

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
