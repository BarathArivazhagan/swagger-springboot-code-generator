package com.barath.codegen.app.util;



import org.springframework.util.StringUtils;

public class PropertyResolverUtility {
	
	
	public static String resolveRelationType(String relationType){
		
		if(StringUtils.isEmpty(relationType)){
			return null;
		}
		switch(relationType.toLowerCase()){
		
			case "onetomany": relationType="OneToMany";break;
			case "manytoone" : relationType="ManyToOne";break;
			case "onetoone" : relationType="OneToOne";break;
			case "manytomany" : relationType="ManyToMany";break; 
			default : relationType="";break;
		
	   }
		
		System.out.println("relation type resolved");
		return relationType;
		
	}
	
	public static String resolveCascadeTypes(String cascadeTypes){
		

		if(StringUtils.isEmpty(cascadeTypes)){
			return "CascadeType.ALL";
		}
		String[] cascades=cascadeTypes.split(",");
		StringBuilder builder=new StringBuilder("{");
		for(String cascade : cascades){
			
			switch (cascade.toLowerCase()) {
				case "all":	  cascade = "CascadeType.ALL";break;
				case "persist":	  cascade = "CascadeType.PERSIST";break; 
				case "remove":	  cascade = "CascadeType.REMOVE";break; 
				case "refresh":	  cascade = "CascadeType.REFRESH";break; 
				case "merge":	  cascade = "CascadeType.MERGE";break; 
				
	
				default: cascade = "CascadeType.ALL";break;
					
			}
			builder.append(cascade);
		}
		
		
		builder.append("}");
		System.out.println("resolved cascade types "+builder.toString());
		return builder.toString();
	}
	
	public static String resolveFetchTypes(String fetchTypes){
		

		if(StringUtils.isEmpty(fetchTypes)){
			return "FetchType.LAZY";
		}
		String[] fetches=fetchTypes.split(",");
		StringBuilder builder=new StringBuilder("{");
		for(String fetch : fetches){
			
			switch (fetch.toLowerCase()) {
				case "eager":	  fetch = "FetchType.EAGER";break;
				case "lazy":	  fetch = "FetchType.LAZY";break; 
			
				
	
				default: fetch = "FetchType.LAZY";break;
					
			}
			builder.append(fetch);
		}
		
		
		builder.append("}");
		System.out.println("resolved fetch types "+builder.toString());
		return builder.toString();
	}
	
	public static String resolvePropertyWithoutHttpMethod(String operationId){
		
		if(operationId.contains("Using")){
			System.out.println("Opertionid "+operationId);
			operationId=operationId.split("Using")[0];
		}
		
		System.out.println("Opertionid "+operationId);
		return operationId;
	}

}
