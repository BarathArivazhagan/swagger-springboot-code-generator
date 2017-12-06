package com.barath.codegen.app.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.barath.codegen.app.generator.CustomSwaggerGenerator;
import com.barath.codegen.app.model.RequestModel;
import com.barath.codegen.app.model.SwaggerConfigOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.codegen.DefaultCodegen;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.parser.SwaggerCompatConverter;



@Service
public class SwaggerCodegenService {
	
	private final RestTemplate restTemplate=new RestTemplate();
	private static final ObjectMapper mapper=new ObjectMapper();
	
	
	@Autowired
	private CustomSwaggerGenerator swaggerGenerator;
	
	
	public void generateSwaggerCodegen(File dir,RequestModel request) {
		
		MultipartFile swaggerFile=request.getSwaggerJson();
		String swaggerJson=null;
		try {
			swaggerJson = mapper.writeValueAsString(mapper.readValue(swaggerFile.getInputStream(), Object.class));
			System.out.println("SWAGGE JSON "+swaggerJson);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		String baseDir=request.getBaseDir();		
		swaggerGenerator.customGenerate(swaggerJson,null,request.getPackageName());
		swaggerGenerator.generateModels(new File(dir,baseDir),request,new ArrayList<>(), new ArrayList<>());
		swaggerGenerator.generateApis(new File(dir,baseDir),new ArrayList<>(), new ArrayList<>());
		
	
	}
	
	protected byte[] generateWithRESTAPI(String swaggerJson) {
		HttpHeaders headers=new HttpHeaders();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		Map<String,Object> requestMap=new HashMap<>();
		requestMap.put("spec", swaggerJson);
		SwaggerConfigOptions configOptions=new SwaggerConfigOptions();
		configOptions.setApiPackage("com.test");
		configOptions.setApiPackage("com.test");
		configOptions.setModelPropertyNaming("camelCase");
		requestMap.put("options", configOptions);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity=new HttpEntity<>(requestMap,headers);
		String url="http://generator.swagger.io/api/gen/clients/java";
		ResponseEntity<byte[]> responseEntity=restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("successfull "+responseEntity.getBody());
			return responseEntity.getBody();
		}
		return null;
	}

}
