package com.dozi.zoominfo.config;

import java.net.URLEncoder;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableR2dbcRepositories(basePackages = { "com.dozi.zoominfo.repo" })
public class MySQLConfig {

	@Configuration
	@PropertySource("classpath:db.properties")
	static class Default {
	}

	@Autowired
	private Environment env;

	@Bean
	@Primary
	public ConnectionPool connectionPool() {

		String r2dbcURL = String.format(env.getProperty("account.dbUrl"),
				// Plug in the decryption here
				URLEncoder.encode(env.getProperty("username")), 
				URLEncoder.encode(env.getProperty("password")));
// 		log.info("r2dbcURL: {}", r2dbcURL);

		ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration
				.builder(ConnectionFactories.get(r2dbcURL)).maxSize(100).maxCreateConnectionTime(Duration.ofSeconds(5))
				.backgroundEvictionInterval(Duration.ofSeconds(60)).validationQuery("SELECT 1").build();
		return new ConnectionPool(configuration);
	}

}
