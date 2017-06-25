package com.barath.app.initializer.ui;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import com.barath.app.model.TemplateProperties;

import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.web.project.AbstractInitializrController;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CustomUiController extends AbstractInitializrController {
	
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
		return "codegen-home";
	}

	private Object getTemplates() {
		if( tempProps !=null && log.isInfoEnabled()){
			log.info("Templates found {}"+tempProps.getTemplates().toString());
		}
		return tempProps.getTemplates();
		
	}



}
