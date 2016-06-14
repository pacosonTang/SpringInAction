package com.spring.spittr.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spring.spittr.web.WebConfig;

public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}


	@Override
	protected String[] getServletMappings() {
		return new String[] { "/", "*.js", "*.css", "*.jpg", "*.png" };
	}

}