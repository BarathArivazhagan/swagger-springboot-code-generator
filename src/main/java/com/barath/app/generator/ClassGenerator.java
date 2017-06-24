package com.barath.app.generator;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.barath.app.model.ClassModel;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

@Component
public class ClassGenerator {
	
	@Autowired
	private MethodGenerator methodGenerator;
	
	
	
	public TypeSpec generateClass(ClassModel classModel){
	

			TypeSpec.Builder builder = TypeSpec.classBuilder(classModel.getName())
			    .addModifiers(Modifier.PUBLIC);
			classModel.getMethods().stream().forEach(  (method) ->{
				try {
					builder.addMethod(methodGenerator.generateMethod(method));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			});    
			TypeSpec clazz=builder.build();
			
		return clazz;
	}

}
