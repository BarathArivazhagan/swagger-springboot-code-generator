package com.barath.codegen.app.initializer.configuration;

import java.util.ArrayList;
import java.util.List;
//import javax.cache.configuration.MutableConfiguration;
//import javax.cache.expiry.CreatedExpiryPolicy;
//import javax.cache.expiry.Duration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.spring.initializr.generator.ProjectGenerator;
import io.spring.initializr.generator.ProjectRequestPostProcessor;
import io.spring.initializr.generator.ProjectRequestResolver;
import io.spring.initializr.generator.ProjectResourceLocator;
import io.spring.initializr.metadata.DependencyMetadataProvider;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.util.TemplateRenderer;
import io.spring.initializr.web.support.DefaultDependencyMetadataProvider;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.ui.UiController;



/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration
 * Auto-configuration} to configure Spring initializr. In a web environment,
 * configures the necessary controller to serve the applications from the
 * root context.
 *
 * <p>Project generation can be customized by defining a custom
 * {@link ProjectGenerator}.
 *
 */
@Configuration
@EnableConfigurationProperties(InitializrProperties.class)
@AutoConfigureAfter(CacheAutoConfiguration.class)
public class InitializerConfiguration {

	private final List<ProjectRequestPostProcessor> postProcessors;

	public InitializerConfiguration(
			ObjectProvider<List<ProjectRequestPostProcessor>> postProcessors) {
		List<ProjectRequestPostProcessor> list = postProcessors.getIfAvailable();
		this.postProcessors = list != null ? list : new ArrayList<>();
	}

//	@Bean
//	public WebConfig webConfig() {
//		return new WebConfig();
//	}

	/*@Bean
	@ConditionalOnMissingBean
	public CustomMainController initializrCustomMainController(
			InitializrMetadataProvider metadataProvider,
			TemplateRenderer templateRenderer,
			ResourceUrlProvider resourceUrlProvider,
			ProjectGenerator projectGenerator,
			DependencyMetadataProvider dependencyMetadataProvider) {
		return new CustomMainController(metadataProvider, templateRenderer, resourceUrlProvider
				, projectGenerator, dependencyMetadataProvider);
	}*/

	@Bean
	@ConditionalOnMissingBean
	public UiController initializrUiController(
			InitializrMetadataProvider metadataProvider) {
		return new UiController(metadataProvider);
	}

	@Bean
	@ConditionalOnMissingBean
	public ProjectGenerator projectGenerator() {
		return new ProjectGenerator();
	}

	@Bean
	@ConditionalOnMissingBean
	public TemplateRenderer templateRenderer(Environment environment) {
		Binder binder = Binder.get(environment);
		boolean cache = binder.bind("spring.mustache.cache", Boolean.class).orElse(true);
		TemplateRenderer templateRenderer = new TemplateRenderer();
		templateRenderer.setCache(cache);
		return templateRenderer;
	}

	@Bean
	@ConditionalOnMissingBean
	public ProjectRequestResolver projectRequestResolver() {
		return new ProjectRequestResolver(postProcessors);
	}

	@Bean
	public ProjectResourceLocator projectResourceLocator() {
		return new ProjectResourceLocator();
	}

	@Bean
	@ConditionalOnMissingBean(InitializrMetadataProvider.class)
	public InitializrMetadataProvider initializrMetadataProvider(
			InitializrProperties properties, ObjectMapper objectMapper,
			RestTemplateBuilder restTemplateBuilder) {
		InitializrMetadata metadata = InitializrMetadataBuilder
				.fromInitializrProperties(properties).build();
		return new DefaultInitializrMetadataProvider(metadata, objectMapper,
				restTemplateBuilder.build());
	}

	@Bean
	@ConditionalOnMissingBean
	public DependencyMetadataProvider dependencyMetadataProvider() {
		return new DefaultDependencyMetadataProvider();
	}



}
