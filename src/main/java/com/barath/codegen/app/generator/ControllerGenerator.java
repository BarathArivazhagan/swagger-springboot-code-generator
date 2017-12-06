package com.barath.codegen.app.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ControllerGenerator extends BaseGenerator{
	

	

	public List<Map<String, Object>> buildControllers(File target, List<Map<String, Object>> models) {
		
		
		return models;
	}
	
	 public void writeControllers(File target,List<Map<String,Object>> input){
			
			
			input.stream().forEach( map -> {
				
				String packageName=(String) map.get("packageName");
				String baseDir=(String) map.get("baseDir");	
				String className=(String)map.get("ClassName");
				File controllerFile = new File(target,baseDir+"/src/main/java/"+packageName.replace('.', '/')+"/controller");
				controllerFile.mkdir();
				write(new File(controllerFile,className.concat("Controller.java")),"restcontroller", map);
			});
			
	 }


}
