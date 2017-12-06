package com.barath.codegen.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import java.util.concurrent.Executor;

import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.web.autoconfigure.InitializrAutoConfiguration;
import io.spring.initializr.web.project.LegacyStsController;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.resource.ResourceUrlProvider;


@SpringBootApplication(exclude={InitializrAutoConfiguration.class})
@EnableCaching
public class MicroserviceCodegenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCodegenApplication.class, args);
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	public LegacyStsController legacyStsController(InitializrMetadataProvider metadataProvider,
			ResourceUrlProvider resourceUrlProvider) {
		return new LegacyStsController(metadataProvider, resourceUrlProvider);
	}

	@Configuration
	@EnableAsync
	static class AsyncConfiguration extends AsyncConfigurerSupport {

		@Override
		public Executor getAsyncExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(1);
			executor.setMaxPoolSize(5);
			executor.setThreadNamePrefix("initializr-");
			executor.initialize();
			return executor;
		}

	}
}
