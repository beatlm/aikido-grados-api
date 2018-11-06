package com.aikido.grados.aikidogrados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AikidoGradosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AikidoGradosApplication.class, args);
	}
	
	@Bean
	  public FilterRegistrationBean corsFilter() {
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");//TODO añadir el allowed correcto y no todos
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	    bean.setOrder(0);
	    return bean;
	  }
}
