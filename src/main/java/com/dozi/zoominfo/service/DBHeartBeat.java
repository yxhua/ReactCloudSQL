package com.dozi.zoominfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@EnableScheduling
public class DBHeartBeat {
	@Autowired
	ConnectionFactory connectionFactory;

	@Scheduled(initialDelay=60000, fixedRate = 60000)
	public void heartbeat() {
		DatabaseClient client = DatabaseClient.create(connectionFactory);
		
		Mono<Map<String, Object>> result = client.sql("select now() as now").fetch().first();
		
		log.info("Getting DB time: {}", result.block().get("now"));
	}

}
