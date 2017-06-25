package com.barath.app.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.barath.app.constant.AppConstants;
import com.barath.app.model.MicroserviceBasicRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MetadataRepository {

	
	private static final ObjectMapper mapper=new ObjectMapper();
	
	public boolean saveMetadata(MicroserviceBasicRequest request){
		
		
			
		Path filePath=Paths.get(AppConstants.METADATA_FOLDER.concat("/").concat(request.getTemplateName()).concat(AppConstants.JSON_EXTENSION));
			
		String templateName=request.getTemplateName();
		if( filePath !=null){
			try {
				if(!filePath.toFile().exists()){
					System.out.println("File is empty"+filePath.toString());
					filePath.toFile().createNewFile();
//					Path newFilePath=Paths.get(AppConstants.METADATA_FOLDER.concat("/"));
//					System.out.println("File is empty"+newFilePath.toAbsolutePath());
					
				}
				mapper.writeValue(filePath.toFile(), (Object)request.getModels());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return true;		
		}
		
		return false;
	}
	
	public Object getMetadata(String templateName){
		
		Object obj=null;
		try {
			obj = mapper.readValue(Paths.get(AppConstants.METADATA_FOLDER.concat("/").concat(templateName).concat(AppConstants.JSON_EXTENSION)).toFile(), Object.class);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("reading the metadata "+obj);
		return obj;
	}

}
