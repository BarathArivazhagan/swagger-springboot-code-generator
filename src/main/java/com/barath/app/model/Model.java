package com.barath.app.model;


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
public class Model {
	
	private String name;
	
	private String type;
	
	private String packageName;
	
	private Object properties;

}
