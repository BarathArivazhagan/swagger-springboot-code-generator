package com.barath.codegen.app.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ServiceGenerator extends BaseGenerator {
	
	
	
	public List<Map<String, Object>> buildServices(File target,List<Map<String,Object>> models){
		
		//do additional processing here
		return models;
	}
	
	 public void writeServices(File target,List<Map<String,Object>> input){
			
			
			input.stream().forEach( map -> {
				
				String packageName=(String) map.get("packageName");
				String baseDir=(String) map.get("baseDir");	
				String className=(String)map.get("ClassName");
				File serviceFile = new File(target,baseDir+"/src/main/java/"+packageName.replace('.', '/')+"/service");
				serviceFile.mkdir();
				write(new File(serviceFile,className.concat("Service.java")),"service", map);
			});
			
	 }

}
