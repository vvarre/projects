package com.spr.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories("com.spr.repo")
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = "classpath:test.properties")
public class DBConfig {

	@Autowired
	Environment env;

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		JpaDialect jpaDialect = new HibernateJpaDialect();
		txManager.setEntityManagerFactory(entityManagerFactory);
		txManager.setJpaDialect(jpaDialect);
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public BoneCPDataSource boneCPDataSource() {

		BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
		//boneCPDataSource.setDriverClass("com.mysql.jdbc.Driver");
		boneCPDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		boneCPDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		boneCPDataSource.setUsername(env.getProperty("jdbc.username"));
		boneCPDataSource.setPassword(env.getProperty("jdbc.password"));
		boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
		boneCPDataSource.setIdleMaxAgeInMinutes(420);
		boneCPDataSource.setMaxConnectionsPerPartition(30);
		boneCPDataSource.setMinConnectionsPerPartition(10);
		boneCPDataSource.setPartitionCount(3);
		boneCPDataSource.setAcquireIncrement(5);
		boneCPDataSource.setStatementsCacheSize(100);
		boneCPDataSource.setReleaseHelperThreads(3);

		return boneCPDataSource;
	}

	@Bean
	@Autowired
	public EntityManagerFactory entityManagerFactory(BoneCPDataSource dataSource) {
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(false);
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
//		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		vendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.spr.model");
		factory.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.setProperty("hibernate.cache.use_query_cache", "true");
		properties.setProperty("hibernate.generate_statistics", "true");
		properties.setProperty("hibernate.show_sql", "true");
//		properties.setProperty("hibernate.hbm2ddl.auto", "validate");

		factory.setJpaProperties(properties);

		factory.afterPropertiesSet();

		return factory.getObject();
	}
}
