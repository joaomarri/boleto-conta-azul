package br.com.contaazul.boleto.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean(name="dataSource")
    public DataSource dataSource() {
    		BasicDataSource dataSource = new BasicDataSource(); 
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("user");
        dataSource.setPassword("senha");
        dataSource.setUrl("jdbc:mysql://localhost:3306/boletodb?profileSQL=false");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(10);
        return dataSource;
    }

    @Bean(name="sql")
    public NamedParameterJdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
}