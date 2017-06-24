package com.barath.app.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.barath.app.generator.ModelGenerator;
import com.barath.app.model.RequestModel;
import com.barath.app.util.JacksonUtils;

@Configuration
public class CodeGenerationInitializer {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private ModelGenerator modelGenerator;
	
	@Value("${model.filePath:null}")
	private String filePath;
	
	@PostConstruct
	public void initializeCodeGeneration(){
		
		if(!StringUtils.isEmpty(filePath)){
			
			RequestModel requestModel=(RequestModel) JacksonUtils.fromJsonFile(filePath,RequestModel.class);
			modelGenerator.generateModel(requestModel);
		}
		
	}

}
