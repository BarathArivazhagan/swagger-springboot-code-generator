package com.barath.app.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.barath.app.model.RequestModel;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

@Component
public class PackageGenerator {
	
	
	@Autowired
	private ClassGenerator classGenerator;
	
	
	public List<JavaFile> generatePackages(RequestModel requestModel){
		
		
		List<JavaFile> javaFiles=new ArrayList<JavaFile>();
		requestModel.getPackages().stream().forEach( (packageModel) -> {
			
			packageModel.getClasses().stream().forEach( (clazzModel) -> {
				
				TypeSpec clazz=classGenerator.generateClass(clazzModel);
				javaFiles.add(JavaFile.builder(packageModel.getName(), clazz).build());
			});	

			
		});
		
		return javaFiles;
	}

}
