package com.barath.app.generator;

import javax.lang.model.element.Modifier;

import org.springframework.stereotype.Component;

import com.barath.app.model.MethodModel;
import com.squareup.javapoet.MethodSpec;

@Component
public class MethodGenerator {
	
	
	public MethodSpec generateMethod(MethodModel method) throws ClassNotFoundException{
		
		MethodSpec main = MethodSpec.methodBuilder(method.getName())
			    .addModifiers(Modifier.PUBLIC)
			    .returns( "void".equalsIgnoreCase(method.getReturns()) ? void.class : Class.forName(method.getReturns()))
			    .addParameter( "String[]".equalsIgnoreCase(method.getInputType()) ? String[].class : Class.forName(method.getInputType()), method.getInputArgName())
			    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
			    .build();
		return main;
	}
	
	
	

}
