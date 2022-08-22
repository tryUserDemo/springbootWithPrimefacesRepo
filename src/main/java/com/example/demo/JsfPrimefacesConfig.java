package com.example.demo;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.sun.faces.config.ConfigureListener;

@Configuration
@PropertySource("classpath:/application.yml")
public class JsfPrimefacesConfig {

	@Value("${jsf.urlMappings}")
	private String urlMappings;
	
	@Value("${jsf.facesServletName}")
	private String facesServletName;
	
	@Value("${jsf.servletContext.forceLoadConfigurationParam}")
	private String forceLoadConfigurationParam;
	
	@Value("${jsf.servletContext.forceLoadConfigurationValue}")
	private String forceLoadConfigurationValue;

	@Value("${jsf.servletContext.primefaces.themeParam}")
	private String primefacesThemeParam;
	
	@Value("${jsf.servletContext.primefaces.themeValue}")
	private String primefacesThemeValue;
	
	@Value("${jsf.servletContext.primefaces.clientSideValidationParam}")
	private String clientSideValidationParam;
	
	@Value("${jsf.servletContext.primefaces.clientSideValidationValue}")
	private String clientSideValidationValue;
	
	@Value("${jsf.servletContext.primefaces.fontParam}")
	private String fontParam;
	
	@Value("${jsf.servletContext.primefaces.fontValue}")
	private String fontValue;
	
	@Value("${jsf.faceletsSkipCommentsParam}")
	private String faceletsSkipCommentsParam;
	
	@Value("${jsf.faceletsSkipCommentsValue}")
	private String faceletsSkipCommentsValue;
	
	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistraiton() {
		ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(
				new FacesServlet(), new String[] {urlMappings});
		registration.setName(facesServletName);
		registration.setLoadOnStartup(1);

		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter(forceLoadConfigurationParam, forceLoadConfigurationValue);
			// Primefacesin ücretsiz temalarından bootstrap örneği yaptık
			// değiştirebilirsiniz
			servletContext.setInitParameter(primefacesThemeParam, primefacesThemeValue); // bootstrap
			// Primefaces client browser tarafında kontrol edilebilme örneğin textbox 10
			// karakter olmalı vs..
			servletContext.setInitParameter(clientSideValidationParam, clientSideValidationValue);
			// primefaces icon set için
			servletContext.setInitParameter(fontParam, fontValue);
			// Xhtml sayfalarında commentlerin parse edilmemesi.
			servletContext.setInitParameter(faceletsSkipCommentsParam, faceletsSkipCommentsValue);
		};
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}
	
}
