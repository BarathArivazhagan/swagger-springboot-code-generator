package com.barath.codegen.app.initializer.ui;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import com.barath.codegen.app.model.TemplateProperties;

import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.web.project.AbstractInitializrController;



@Controller
public class CustomUiController extends AbstractInitializrController {
	
	private static final Logger logger=LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private TemplateProperties tempProps;

	
	protected CustomUiController(InitializrMetadataProvider metadataProvider,
			ResourceUrlProvider resourceUrlProvider) {
		super(metadataProvider, resourceUrlProvider);
		
	}

	@RequestMapping(value = "/", produces = "text/html")
	public String codegenHome(Map<String, Object> model) {
		renderHome(model);
		model.put("templatetitle", "Choose from existing template");
		model.put("templates", getTemplates());
		model.put("buildVersion", "Build Version");
		return "codegen-home";
	}

	private Object getTemplates() {
		if( tempProps !=null && logger.isInfoEnabled()){
			logger.info("Templates found in metadata {}",Objects.toString(tempProps.getTemplates()));
		}
		return tempProps.getTemplates();
		
	}



}
