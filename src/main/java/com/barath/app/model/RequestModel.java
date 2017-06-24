package com.barath.app.model;

import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RequestModel {
	
	private String name;
	
	private String groupId;
	
	private String artifactId;
	
	private String buildManagement;
	
	private String[] dependencies;
	
	private String[] template;
	
	private Set<Package> packages;
	
	

}
