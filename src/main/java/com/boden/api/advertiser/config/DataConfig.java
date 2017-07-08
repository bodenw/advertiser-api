package com.boden.api.advertiser.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.boden.api.advertiser.controller.AdvertiserController;

@Configuration
@MapperScan("com.boden.api.advertiser.mapper")
public class DataConfig {

	private final Logger logger = LoggerFactory.getLogger(AdvertiserController.class);	
	
	@Bean
	public DataSource dataSource() {
		// Build DataSource
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = builder
				.setType(EmbeddedDatabaseType.H2).build();
	    
        // create a table and populate some data
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        logger.info("Creating table");
        jdbcTemplate.execute("drop table advertiser if exists");
        jdbcTemplate.execute("create table advertiser(id char(36), name varchar(50), contactName varchar(100), creditLimit int)");
        jdbcTemplate.update("insert into advertiser(id, name, contactName, creditLimit) values (?,?,?,?)", 
        		"12345678-1234-1234-1234-123456789ABC", "Testing", "Test User", 1000);
        return dataSource;
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
	
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.boden.api.advertiser.model");
        return sessionFactory;
    }
}
