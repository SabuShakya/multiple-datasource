package com.sabu.multipledatasource.config;

import com.sabu.multipledatasource.annotation.ReadOnlyRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.sabu.multipledatasource.repository.readwrite",
//        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "primaryEntityManagerFactory"
)
@Getter
@Setter
public class PrimaryDataSourceConfiguration {

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
    public PrimaryDataSourceConfiguration(@Value("${spring.datasource.url}") String url,
                                          @Value("${spring.datasource.username}") String username,
                                          @Value("${spring.datasource.password}") String password,
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

    @Bean()
    @Primary
    public DataSource primaryDataSource() {
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

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(poolProperties);
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

//        vendorAdapter.setGenerateDdl(false);
//        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
//        properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        properties.put("hibernate.show_sql", String.valueOf(showSQL));
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(primaryDataSource());
////        factoryBean.setPackagesToScan("com.sabu.multipledatasource");
//        factoryBean.setPackagesToScan("com.sabu.multipledatasource.entity");
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//        factoryBean.getJpaPropertyMap().putAll(properties);
//        return factoryBean;
        return entityManagerFactoryBuilder
                .dataSource(primaryDataSource())
                .packages("com.sabu.multipledatasource.entity.common",
                        "com.sabu.multipledatasource.entity.master")
                .build();
    }

}
