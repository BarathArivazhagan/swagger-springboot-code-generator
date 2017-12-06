package com.barath.codegen.app.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;




public class JacksonUtils {
	
	private static final Logger logger=LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final static ObjectMapper mapper=new ObjectMapper();
	
	static{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	
	public static String toJson(Object input){
		
			String output=null;
			if(input !=null){
				try {
					output=mapper.writeValueAsString(input);
				} catch (JsonProcessingException e) {
					logger.error("Error in converting object to json");
					logger.error("Error ",e);
					e.printStackTrace();
				}
			}
			return output;
	}
	
	
	public static Object fromJson(String input,Class<?> clazz){
		
		Object output=null;
		if(!StringUtils.isEmpty(input) && clazz !=null){
			try {
				output=mapper.readValue(input, clazz);
			} catch (IOException e) {
				logger.error("Error in converting json to object");
				logger.error("Error ",e);
				e.printStackTrace();
			}
		}
		return output;
	}
	
	public static Object fromJsonFile(String filePath){
		
		String output=null;
		if(!StringUtils.isEmpty(filePath)){
			try {
				mapper.readValue(new File(filePath), Object.class);
			} catch (IOException e) {
				logger.error("Error in converting file to json");
				logger.error("Error ",e);
				e.printStackTrace();
			}
		}
		return output;
	}
	
	public static Object fromJsonFile(String filePath,Class<?> clazz){
		
		Object output=null;
		if(!StringUtils.isEmpty(filePath) && clazz !=null){
			try {
				output=mapper.readValue(new File(filePath), clazz);
			} catch (IOException e) {
				logger.error("Error in converting file to json");
				logger.error("Error ",e);
				e.printStackTrace();
			}
		}
		return output;
	}
	
	public static Object fromJsonResource(Resource resource){
		
		Object output=null;
		if(resource !=null){
			try {
				output=mapper.readValue(resource.getInputStream(),Object.class) ;
			} catch (IOException e) {
				logger.error("Error in converting file resource to json");
				logger.error("Error ",e);
				e.printStackTrace();
			}
		}
		return output;
	}
	
	public static Object fromJsonStream(InputStream stream){
		
		Object output=null;
		if(stream !=null){
			try {
				output=mapper.readValue(stream,Object.class) ;
			} catch (IOException e) {
				logger.error("Error in converting file resource to json");
				logger.error("Error ",e);
				e.printStackTrace();
			}
		}
		return output;
	}
	
	
	public static ObjectMapper getObjectMapper(){
		return mapper;
	}

}
