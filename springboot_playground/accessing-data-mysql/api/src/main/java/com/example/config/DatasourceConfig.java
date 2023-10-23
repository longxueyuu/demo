package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    @Bean(name = "xdataSource")
    @ConfigurationProperties(prefix = "x.datasource")
    public DataSource xdataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    @Bean(name = "yDataSource")
    @ConfigurationProperties(prefix = "y.datasource")
    public DataSource ydataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    @Bean(name="xjdbcTemplate")
    public JdbcTemplate xjdbcTemplate(@Qualifier(value = "xdataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "xtransactionManager")
    public PlatformTransactionManager xdataSourceTransactionManager(@Qualifier("xdataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name="yjdbcTemplate")
    public JdbcTemplate yjdbcTemplate(@Qualifier(value = "yDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "yTransactionManager")
    public PlatformTransactionManager ydataSourceTransactionManager(@Qualifier("yDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
