package org.gwaraujo.account.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetAccountBalanceTest {

	private GetAccountBalance getAccountBalance;

	@BeforeEach
	void setUp() {
		getAccountBalance = new GetAccountBalance();
	}

	@Test
	void shouldReturnValidBalance() {
		BigDecimal originalBalance = new BigDecimal(1500);
		Account account = Account.builder().id(1L).balance(originalBalance).build();

		BigDecimal balance = getAccountBalance.getBalance(account);

		assertNotNull(balance);
		assertEquals(originalBalance, balance);
	}

	@Test
	void shouldFailWhenAccountIsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> getAccountBalance.getBalance(null));
	}

}
