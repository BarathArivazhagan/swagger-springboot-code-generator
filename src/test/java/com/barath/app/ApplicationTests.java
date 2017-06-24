package com.barath.app;

import java.io.File;
import java.io.IOException;

import org.hibernate.result.Output;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.barath.app.model.RequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {
	
	private static final ObjectMapper testMapper=new ObjectMapper();
	
	@Value("${model.filePath}")
	private String filePath;
	
	@Test
	public void readJsonFromFile() {
		
			
		try {
			Object output=testMapper.readValue(new File(filePath), RequestModel.class);
			System.out.println("OUTPUT "+output.toString());
			RequestModel model=(RequestModel) output;
			System.out.println("Request Model "+model.toString());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
