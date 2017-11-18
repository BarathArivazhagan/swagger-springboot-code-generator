package com.barath.app.model;

import java.util.Set;



public class ClassModel {
	
	
	private String name;
	
	private Set<MethodModel> methods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MethodModel> getMethods() {
		return methods;
	}

	public void setMethods(Set<MethodModel> methods) {
		this.methods = methods;
	}

	public ClassModel(String name, Set<MethodModel> methods) {
		super();
		this.name = name;
		this.methods = methods;
	}

	public ClassModel() {
		super();
		
	}
	
	
	

}
