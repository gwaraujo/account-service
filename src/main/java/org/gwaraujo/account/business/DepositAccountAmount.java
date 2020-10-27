package org.gwaraujo.account.business;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;

public class DepositAccountAmount {

	public Account deposit(Account account, BigDecimal amount) {
		validate(account, amount);

		var originalAmount = account.getBalance();
		var newAmount = originalAmount.add(amount);
		account.setBalance(newAmount);

		return account;
	}

	private void validate(Account account, BigDecimal amount) {
		if (account == null) {
			throw new IllegalArgumentException("Account should be valid.");
		}

		if (amount == null) {
			throw new IllegalArgumentException("Amount should be valid.");
		}
	}

}
