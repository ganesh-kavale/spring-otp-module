package com.sapours.login.SendOTP.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sapours.login.SendOTP.Pojo.Email;
import com.sapours.login.SendOTP.Pojo.Logic;
import com.sapours.login.SendOTP.Pojo.Registration;
import com.sapours.login.SendOTP.Pojo.RegistrationDao;

@Configuration
@ComponentScan(basePackages="com.sapours.login.SendOTP")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	@Bean
	public Email emailMethod() {
		return new Email();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


	@Bean
	public Logic logicMethod() {
		return new Logic();
	}
	
	//------------------------------------------------------------
	
	
	@Bean
	public Registration registrationMethod() {
		return new Registration();
	}
	@Bean
	public RegistrationDao registrationDaoMethod() {
		RegistrationDao registrationDao=new RegistrationDao();
		registrationDao.setTemplate(jdbcTemplate());
		return registrationDao;
	}
	
	@Bean
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    //MySQL database we are using
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/miniproject");
	    dataSource.setUsername("root");
	    dataSource.setPassword("Ganesh@123");
		return dataSource;
	}
	
	
	
//	@Bean
//	  public DataSource dataSource() {
//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	    //MySQL database we are using
//	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	    dataSource.setUrl("jdbc:mysql://localhost:3306/miniproject");//for fileupload
//	    dataSource.setUsername("root");
//	    dataSource.setPassword("Ganesh@123");
//		return dataSource;
//	}
	
	 @Bean
	  public JdbcTemplate jdbcTemplate() {
	    JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    jdbcTemplate.setDataSource(dataSource());
	    return jdbcTemplate;
	  }
	 
	 
	 //------------------------------------------------
	 
	 @Bean
	    public MultipartResolver multipartResolver() {
	        return new CommonsMultipartResolver();
	   }
	
}
