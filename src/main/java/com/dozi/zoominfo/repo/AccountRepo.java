package com.dozi.zoominfo.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.dozi.zoominfo.model.Account;

import reactor.core.publisher.Flux;

public interface AccountRepo extends ReactiveCrudRepository<Account, String> {
	Flux<Account> findByUsername(String username);
}