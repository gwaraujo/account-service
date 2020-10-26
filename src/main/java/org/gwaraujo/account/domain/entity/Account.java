package org.gwaraujo.account.domain.entity;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Account {

	private Long id;
	private BigDecimal balance;

}
