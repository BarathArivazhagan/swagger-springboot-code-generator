package com.barath.app.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.barath.app.model.RequestModel;
import com.barath.app.util.JacksonUtils;
import com.squareup.javapoet.JavaFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ModelGenerator {
	
	
	@Autowired
	private PackageGenerator packageGenerator;
	
	private static final Path path=new File("/Users/barath/Eclipseworkspace/pluginws/microservice-codegen-tool/src/main/resources").toPath();
	
	public Object generateModel(RequestModel requestModel){
		
		System.out.println(JacksonUtils.toJson(requestModel));
		List<JavaFile> files=packageGenerator.generatePackages(requestModel);
		files.stream().forEach((javaFile) -> {
			
			try {
				javaFile.writeTo(path);
			} catch (IOException e) {
				log.error("error",e);
				e.printStackTrace();
			}
			
		});

		return files;
	}
}
