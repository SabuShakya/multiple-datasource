package com.sabu.multipledatasource.config;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.sabu.multipledatasource.repository.readonly",
//        includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "readOnlyEntityManagerFactory"
)
public class ReadOnlyDataSourceConfiguration {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer tomcatMaxActiveConnections;     // Max active connections during any heavy load
    private Integer tomcatMaxIdleConnections;       // Min active connections during low load
    private Integer tomcatTimeBetweenEvictionRunsInMillis;
    private Integer tomcatMinTimeForEvictionEligibilityInMillis;
    private Boolean tomcatRemoveAbandonedConnections;
    private Boolean showSQL;

    @Autowired
    public ReadOnlyDataSourceConfiguration(@Value("${spring.datasource.read_url}") String url,
                                           @Value("${spring.datasource.read_username}") String username,
                                           @Value("${spring.datasource.read_password}") String password,
                                           @Value("${spring.datasource.driver-class-name}") String driverClassName,
                                           @Value("${spring.datasource.tomcat.max-active}") Integer tomcatMaxActiveConnections,
                                           @Value("${spring.datasource.tomcat.max-idle}") Integer tomcatMaxIdleConnections,
                                           @Value("${spring.datasource.tomcat.time-between-eviction-runs-millis}") Integer tomcatTimeBetweenEvictionRunsInMillis,
                                           @Value("${spring.datasource.tomcat.min-evictable-idle-time-millis}") Integer tomcatMinTimeForEvictionEligibilityInMillis,
                                           @Value("${spring.datasource.tomcat.remove-abandoned}") Boolean tomcatRemoveAbandonedConnections,
                                           @Value("${spring.jpa.show-sql}") Boolean showSQL) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
        this.tomcatMaxActiveConnections = tomcatMaxActiveConnections;
        this.tomcatMaxIdleConnections = tomcatMaxIdleConnections;
        this.tomcatTimeBetweenEvictionRunsInMillis = tomcatTimeBetweenEvictionRunsInMillis;
        this.tomcatMinTimeForEvictionEligibilityInMillis = tomcatMinTimeForEvictionEligibilityInMillis;
        this.tomcatRemoveAbandonedConnections = tomcatRemoveAbandonedConnections;
        this.showSQL = showSQL;
    }

    @Bean(value = "readonly")
    public DataSource readDataSource() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(url);
        poolProperties.setUsername(username);
        poolProperties.setPassword(password);
        poolProperties.setDriverClassName(driverClassName);
        poolProperties.setMaxActive(tomcatMaxActiveConnections);
        poolProperties.setMaxIdle(tomcatMaxIdleConnections);
        poolProperties.setTimeBetweenEvictionRunsMillis(tomcatTimeBetweenEvictionRunsInMillis);
        poolProperties.setMinEvictableIdleTimeMillis(tomcatMinTimeForEvictionEligibilityInMillis);
        poolProperties.setRemoveAbandoned(tomcatRemoveAbandonedConnections);

        org.apache.tomcat.jdbc.pool.DataSource readDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        readDataSource.setPoolProperties(poolProperties);
        return readDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean readOnlyEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder
                .dataSource(readDataSource())
                .packages("com.sabu.multipledatasource.entity.common",
                        "com.sabu.multipledatasource.entity.slave")
                .build();
    }

}
