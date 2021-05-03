package com.dozi.zoominfo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * Account
 */
@Validated
@Table("account")
@Data
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int accountId;
	private String username;
}
