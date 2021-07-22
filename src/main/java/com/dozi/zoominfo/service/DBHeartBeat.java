package com.dozi.zoominfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DBHeartBeat {
	@Autowired
	ConnectionFactory connectionFactory;

	//@Scheduled(initialDelay = 5000, fixedRate = 5000)
	public void heartbeat() {
		DatabaseClient client = DatabaseClient.create(connectionFactory);

		Mono<Map<String, Object>> result = client.sql("select now() as now").fetch().first();

		log.info("Getting DB time: {}", result.block().get("now"));
	}

}
