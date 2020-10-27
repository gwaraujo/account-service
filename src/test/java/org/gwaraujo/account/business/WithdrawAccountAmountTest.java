package org.gwaraujo.account.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawAccountAmountTest {

	private WithdrawAccountAmount withdrawAccountAmount;

	@BeforeEach
	void setUp() {
		withdrawAccountAmount = new WithdrawAccountAmount();
	}

	@Test
	void shouldWithdrawAmount() {
		BigDecimal originalBalance = new BigDecimal(1500);
		Account account = Account.builder().id(1L).balance(originalBalance).build();

		BigDecimal amount = new BigDecimal(200);

		Account updatedAccount = withdrawAccountAmount.withdraw(account, amount);

		assertEquals(originalBalance.subtract(amount), updatedAccount.getBalance());
	}

	@Test
	void shouldFailWhenAccountIsInvalid() {
		BigDecimal amount = new BigDecimal(200);
		assertThrows(IllegalArgumentException.class, () -> withdrawAccountAmount.withdraw(null, amount));
	}

	@Test
	void shouldFailWhenAmountIsInvalid() {
		Account account = Account.builder().id(1L).balance(new BigDecimal(1500)).build();
		assertThrows(IllegalArgumentException.class, () -> withdrawAccountAmount.withdraw(account, null));
	}

}
