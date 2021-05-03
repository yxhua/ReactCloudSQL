package com.dozi.zoominfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dozi.zoominfo.model.Account;
import com.dozi.zoominfo.repo.AccountRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.OPTIONS }, allowedHeaders = { "Content-Type", "authorization" })
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	AccountRepo accountRepo;

	@GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<Account> getByUsername(@PathVariable("username") String username) {
		log.info("getByUsername() with {}", username);
		return accountRepo.findByUsername(username);
	}
}
