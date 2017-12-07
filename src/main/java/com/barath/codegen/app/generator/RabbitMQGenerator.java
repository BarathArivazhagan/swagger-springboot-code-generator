package com.barath.codegen.app.generator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.barath.codegen.app.rabbitmq.model.RabbitmqRequestModel;

@Component
public class RabbitMQGenerator extends BaseGenerator {
	
	
	
	
	public List<Map<String,Object>> buildQueues(RabbitmqRequestModel request){
		
		
		
		return Collections.emptyList();
		
	}

}
