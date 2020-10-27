package org.gwaraujo.account.business;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;

public class GetAccountBalance {

	public BigDecimal getBalance(Account account) {
		validate(account);
		return account.getBalance();
	}

	private void validate(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("Account not found. Please enter a valid id.");
		}
	}

}
