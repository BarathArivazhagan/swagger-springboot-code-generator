package com.barath.codegen.app.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryGenerator extends BaseGenerator {

	
	
	
	public List<Map<String, Object>> buildRepositories(File target,List<Map<String,Object>> models){
		
		
		//do additional processing here
		return models;
		
	}
	
	
   public void writeRepositories(File target,List<Map<String,Object>> input){
		
		
		input.stream().forEach( map -> {
			
			String packageName=(String) map.get("packageName");
			String baseDir=(String) map.get("baseDir");	
			String className=(String)map.get("ClassName");
			File repoFile = new File(target,baseDir+"/src/main/java/"+packageName.replace('.', '/')+"/repository");
			repoFile.mkdir();
			write(new File(repoFile,className+"Repository.java"),"repository", map);
		});
		
		
	}
}
