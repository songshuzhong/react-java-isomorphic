package com.bonc.epm.ui;

import com.bonc.epm.ui.renderEngine.utils.ReactEngineFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public FilterRegistrationBean initEPMReactFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new ReactEngineFilter());
        registrationBean.addUrlPatterns("/*");
		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return registrationBean;
    }
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

}
