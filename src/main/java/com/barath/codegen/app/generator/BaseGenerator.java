package com.barath.codegen.app.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import com.barath.codegen.app.util.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.spring.initializr.util.TemplateRenderer;

public abstract class BaseGenerator {
	
	@Autowired
	private TemplateRenderer templateRenderer = new TemplateRenderer();
	
	
	protected void write(File target, String templateName, Map<String, Object> model) {
		String body = templateRenderer.process(templateName, model);
		writeText(target, body);
	}

	protected void writeText(File target, String body) {
		try (OutputStream stream = new FileOutputStream(target)) {
			StreamUtils.copy(body, Charset.forName("UTF-8"), stream);
		}
		catch (Exception e) {
			throw new IllegalStateException("Cannot write file " + target, e);
		}
	}
	
	protected ObjectMapper getObjectMapper(){
		return JacksonUtils.getObjectMapper();
	}

}
