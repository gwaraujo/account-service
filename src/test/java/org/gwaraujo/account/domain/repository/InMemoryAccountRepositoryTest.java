package org.gwaraujo.account.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.repository.inMemory.InMemoryAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryAccountRepositoryTest {

	private InMemoryAccountRepository repository;

	@BeforeEach
	void setUp() {
		repository = new InMemoryAccountRepository();
	}

	@Test
	void shouldSaveNewAccount() {
		var newAccount = Account.builder().id(1L).balance(new BigDecimal(15000)).build();
		repository.save(newAccount);

		var account = repository.findById(1L);

		assertTrue(account.isPresent());
		assertEquals(newAccount, account.get());
	}

	@Test
	void shouldFindExistsAccount() {
		var newAccount = Account.builder().id(1L).balance(new BigDecimal(15000)).build();
		repository.save(newAccount);

		var account = repository.findById(1L);

		assertTrue(account.isPresent());
	}

	@Test
	void shouldNotFindExistsAccount() {
		var account = repository.findById(1L);
		assertFalse(account.isPresent());
	}

	@Test
	void shouldFindAllAccounts() {
		var newAccounts = List.of(Account.builder().id(1L).balance(new BigDecimal(15000)).build(),
				Account.builder().id(2L).balance(new BigDecimal(10000)).build(),
				Account.builder().id(3L).balance(new BigDecimal(200)).build());

		newAccounts.forEach(a -> repository.save(a));
		var accounts = repository.findAll();

		assertIterableEquals(newAccounts, accounts);
	}

	@Test
	void shouldNotFindAnyAccounts() {
		var accounts = repository.findAll();
		assertTrue(accounts.isEmpty());
	}

	@Test
	void shouldDeleteAllAccounts() {
		var newAccounts = List.of(Account.builder().id(1L).balance(new BigDecimal(15000)).build(),
				Account.builder().id(2L).balance(new BigDecimal(10000)).build(),
				Account.builder().id(3L).balance(new BigDecimal(200)).build());

		newAccounts.forEach(a -> repository.save(a));

		repository.deleteAll();

		var accounts = repository.findAll();
		assertTrue(accounts.isEmpty());
	}

}
