package br.com.finance.financeiro_pessoal.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		ErrorPage error400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
		ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
		ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
		return (container -> container.addErrorPages(error400, error404, error403, error500));
	}
	
}
