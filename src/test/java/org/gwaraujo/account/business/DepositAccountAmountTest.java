package org.gwaraujo.account.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositAccountAmountTest {

	private DepositAccountAmount depositAccountAmount;

	@BeforeEach
	void setUp() {
		depositAccountAmount = new DepositAccountAmount();
	}

	@Test
	void shouldDepositAmount() {
		BigDecimal originalBalance = new BigDecimal(1500);
		Account account = Account.builder().id(1L).balance(originalBalance).build();

		BigDecimal amount = new BigDecimal(200);

		Account updatedAccount = depositAccountAmount.deposit(account, amount);

		assertEquals(originalBalance.add(amount), updatedAccount.getBalance());
	}

	@Test
	void shouldFailWhenAccountIsInvalid() {
		BigDecimal amount = new BigDecimal(200);
		assertThrows(IllegalArgumentException.class, () -> depositAccountAmount.deposit(null, amount));
	}

	@Test
	void shouldFailWhenAmountIsInvalid() {
		Account account = Account.builder().id(1L).balance(new BigDecimal(1500)).build();
		assertThrows(IllegalArgumentException.class, () -> depositAccountAmount.deposit(account, null));
	}

}
