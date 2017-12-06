package com.barath.codegen.app.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SwaggerUtils {
	
	
	private final static ObjectMapper mapper=new ObjectMapper();
	
	
	public static Map<String,Object> getSwaggerMap(MultipartFile swaggerFile){
		
		Map<String,Object> swaggerMap=new HashMap<>();
		try {
			swaggerMap=mapper.readValue(swaggerFile.getBytes(), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return swaggerMap;
	}

}
