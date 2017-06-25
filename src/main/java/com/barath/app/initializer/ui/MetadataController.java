package com.barath.app.initializer.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ActuatorMetricWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barath.app.model.MicroserviceBasicRequest;
import com.barath.app.service.MetadataService;

@RestController
public class MetadataController {
	
	
	@Autowired
	private MetadataService service;
	
	@PostMapping(value="/saveMsModel")
	public ResponseEntity<String> handleMicroserviceReq(@RequestBody MicroserviceBasicRequest request){
		ResponseEntity<String> response=null;
		if(request !=null){
			if(service.saveMicroserviceMetadata(request)){
				response=new ResponseEntity<String>("successfully saved",HttpStatus.OK);
			}else{
				response=new ResponseEntity<String>("Failed in saving the metadata",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return response;
		
	}

}
