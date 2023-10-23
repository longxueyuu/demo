package com.example.config;

import com.example.jpa.User;
import com.example.jpa.UserRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class, entityManagerFactoryRef = "customJPAEntityManagerFactory",
        transactionManagerRef = "customJPATransactionManager")
public class JPAConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource customJPADataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    @Primary
    @Bean(name = "customJPAEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customJPAEntityManagerFactory() {
        var jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.MYSQL);

        var factoryBean = new LocalContainerEntityManagerFactoryBean();

        // customJPADataSource()被@Bean注释，默认调用会返回同一个bean，多实例需要使用@Scope属性
        factoryBean.setDataSource(customJPADataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        // 扫描repository类
        factoryBean.setPackagesToScan(User.class.getPackage().getName());

        return factoryBean;
    }


    @Bean(name = "customJPATransactionManager")
    public PlatformTransactionManager customJPATransactionManager() {
        return new JpaTransactionManager(customJPAEntityManagerFactory().getObject());
    }
}
