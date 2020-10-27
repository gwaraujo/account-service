package org.gwaraujo.account.business;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;

public class CreateAccount {

	public Account create(Long accountId) {
		validate(accountId);
		return Account.builder().id(accountId).balance(BigDecimal.ZERO).build();
	}

	private void validate(Long accountId) {
		if (accountId == null || accountId == 0) {
			throw new IllegalArgumentException("Account ID should be valid.");
		}
	}

}
