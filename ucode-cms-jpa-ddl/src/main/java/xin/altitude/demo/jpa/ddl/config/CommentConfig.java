package xin.altitude.demo.jpa.ddl.config;

import org.hibernate.integrator.spi.Integrator;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.altitude.demo.jpa.ddl.support.CommentIntegrator;
import xin.altitude.demo.jpa.ddl.support.CustomHibernateProperties;


@Configuration
public class CommentConfig {

	@Bean
	public Integrator commentIntegrator(){
		return new CommentIntegrator();
	}

	@Bean
	public HibernatePropertiesCustomizer hibernateProperties(){
		return new CustomHibernateProperties();
	}
}
