package org.gwaraujo.account.domain.repository.inMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.repository.AccountRepository;

public class InMemoryAccountRepository implements AccountRepository {

	private Set<Account> accounts;

	public InMemoryAccountRepository() {
		accounts = new CopyOnWriteArraySet<Account>();
	}

	@Override
	public Account save(Account account) {
		accounts.add(account);
		return account;
	}

	@Override
	public Optional<Account> findById(Long accountId) {
		return accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst();
	}

	@Override
	public List<Account> findAll() {
		return new ArrayList<Account>(accounts);
	}

	@Override
	public void deleteAll() {
		accounts.clear();

	}

}
