package org.gwaraujo.account.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateAccountTest {

	private CreateAccount createAccount;

	@BeforeEach
	void setUp() {
		createAccount = new CreateAccount();
	}

	@Test
	void shouldCreateValidAccount() {
		var id = 10L;
		var account = createAccount.create(id);

		assertNotNull(account);
		assertEquals(id, account.getId());
	}

	@Test
	void shouldFailWhenThereIsNoId() {
		assertThrows(IllegalArgumentException.class, () -> createAccount.create(null));
	}

}
