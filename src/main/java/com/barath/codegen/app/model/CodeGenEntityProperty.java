package com.barath.codegen.app.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CodeGenEntityProperty {

    private String type;

    private boolean primaryKey;
    	
    private boolean isAutoGenerated;
    
    private String generationType="auto";
    
    private boolean isMap;
    
    private boolean isList;
    
    private boolean isSet; 
    
    private Map<String,Object> relation;
    
    private String joinColumn;
    
    private String mappedBy;
    
    
    
    
    
    
    
 
    public Map<String, Object> getRelation() {
		return relation;
	}

	public void setRelation(Map<String, Object> relation) {
		this.relation = relation;
	}

	@JsonProperty(value="isMap")  
    public boolean isMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}
	
	 @JsonProperty(value="isList")  
	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

	 @JsonProperty(value="isSet")  
	public boolean isSet() {
		return isSet;
	}

	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

	@JsonProperty(value="isAutoGenerated")
	public boolean isAutoGenerated() {
		return isAutoGenerated;
	}

	public void setAutoGenerated(boolean isAutoGenerated) {
		this.isAutoGenerated = isAutoGenerated;
	}

	public String getGenerationType() {
		return generationType;
	}

	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}

	public String getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
    
    
}