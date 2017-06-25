package com.barath.app.initializer.configuration;

import java.util.ArrayList;
import java.util.List;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

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
import io.spring.initializr.web.autoconfigure.WebConfig;
import io.spring.initializr.web.project.MainController;
import io.spring.initializr.web.support.DefaultDependencyMetadataProvider;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.ui.UiController;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import com.barath.app.initializer.ui.CustomMainController;

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

	@Bean
	public WebConfig webConfig() {
		return new WebConfig();
	}

	@Bean
	@ConditionalOnMissingBean
	public CustomMainController initializrCustomMainController(
			InitializrMetadataProvider metadataProvider,
			TemplateRenderer templateRenderer,
			ResourceUrlProvider resourceUrlProvider,
			ProjectGenerator projectGenerator,
			DependencyMetadataProvider dependencyMetadataProvider) {
		return new CustomMainController(metadataProvider, templateRenderer, resourceUrlProvider
				, projectGenerator, dependencyMetadataProvider);
	}

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
		RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment,
				"spring.mustache.");
		boolean cache = resolver.getProperty("cache", Boolean.class, true);
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
			InitializrProperties properties) {
		InitializrMetadata metadata = InitializrMetadataBuilder
				.fromInitializrProperties(properties).build();
		return new DefaultInitializrMetadataProvider(metadata, new RestTemplate());
	}

	@Bean
	@ConditionalOnMissingBean
	public DependencyMetadataProvider dependencyMetadataProvider() {
		return new DefaultDependencyMetadataProvider();
	}

	@Configuration
	@ConditionalOnClass(javax.cache.CacheManager.class)
	static class CacheConfiguration {

		@Bean
		public JCacheManagerCustomizer initializrCacheManagerCustomizer() {
			return cm -> {
				cm.createCache("initializr", config().setExpiryPolicyFactory(
						CreatedExpiryPolicy.factoryOf(Duration.TEN_MINUTES)));
				cm.createCache("dependency-metadata", config());
				cm.createCache("project-resources", config());
			};
		}

		private MutableConfiguration<Object, Object> config() {
			return new MutableConfiguration<>()
					.setStoreByValue(false)
					.setManagementEnabled(true).setStatisticsEnabled(true);
		}

	}

}
