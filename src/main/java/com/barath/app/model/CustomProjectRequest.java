package com.barath.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import groovy.transform.ToString;
import io.spring.initializr.generator.BasicProjectRequest;
import io.spring.initializr.generator.ProjectRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomProjectRequest extends ProjectRequest{
	
	private String template;
	
	@Override
	public void setVersion(String version) {
		// TODO Auto-generated method stub
		super.setVersion(version);
	}
	
}
