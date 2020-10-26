package org.gwaraujo.account.domain.repository;

import java.util.List;
import java.util.Optional;

import org.gwaraujo.account.domain.entity.Account;

public interface AccountRepository {

	Account save(Account account);

	Optional<Account> findById(Long accountId);

	List<Account> findAll();

	void deleteAll();

}
