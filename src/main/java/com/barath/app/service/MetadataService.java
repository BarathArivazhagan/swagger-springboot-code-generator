package com.barath.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barath.app.model.MicroserviceBasicRequest;
import com.barath.app.repository.MetadataRepository;

@Service
public class MetadataService {
	
	@Autowired
	private MetadataRepository repo;
	
	public boolean saveMicroserviceMetadata(MicroserviceBasicRequest request){
		return repo.saveMetadata(request);
	}
	
	public Object getMicroserviceMetadata(String templateName){
		return repo.getMetadata(templateName);
	}
	

}
