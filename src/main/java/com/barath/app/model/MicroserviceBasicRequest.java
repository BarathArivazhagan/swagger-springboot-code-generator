package com.barath.app.model;

import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
public class MicroserviceBasicRequest {
	
	private String templateName;
	private Set<Model> models;

}
