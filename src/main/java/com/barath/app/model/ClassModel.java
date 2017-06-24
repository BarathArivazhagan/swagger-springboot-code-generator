package com.barath.app.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ClassModel {
	
	
	private String name;
	
	private Set<MethodModel> methods;

}
