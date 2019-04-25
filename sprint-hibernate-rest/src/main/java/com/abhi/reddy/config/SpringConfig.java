package com.abhi.reddy.config;

import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@EnableWebMvc
@ComponentScan(basePackages = "com.abhi.reddy")
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class SpringConfig extends WebMvcConfigurerAdapter {
	
	 @Autowired
	 private Environment env;
	 
	 @Bean
	 public BasicDataSource getDataSource() {
	      BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName(env.getProperty("database.driver"));
	      dataSource.setUrl(env.getProperty("database.url"));
	      dataSource.setUsername(env.getProperty("database.user"));
	      dataSource.setPassword(env.getProperty("database.password"));
	      return dataSource;
	   }

	 @Bean
	 public LocalSessionFactoryBean getSessionFactory() {
	      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	      factoryBean.setDataSource(getDataSource());
	      
	      Properties props=new Properties();
	      props.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
	      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	      props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

	      factoryBean.setHibernateProperties(props);
	      factoryBean.setPackagesToScan(new String[] { "com.abhi.reddy.model" });
	  
	      return factoryBean;
	   }

	   @Bean
	   public HibernateTransactionManager getTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(getSessionFactory().getObject());
	      return transactionManager;
	   }
	   
	   @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/pages/");
	        viewResolver.setSuffix(".html");
	        return viewResolver;
	    }

	   
	   @Bean
	   public DataSourceInitializer dataSourceInitializer() {
	       ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	       resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

	       DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	       dataSourceInitializer.setDataSource(getDataSource());
	       dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	       return dataSourceInitializer;
	   }
	   
	   public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

	        ObjectMapper mapper = new ObjectMapper();
	        mapper.registerModule(new Hibernate5Module());

	        messageConverter.setObjectMapper(mapper);
	        return messageConverter;

	    }

	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(jacksonMessageConverter());
	        super.configureMessageConverters(converters);
	    }
}
