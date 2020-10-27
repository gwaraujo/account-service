package org.gwaraujo.account.service;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.repository.AccountRepository;
import org.gwaraujo.account.dto.AccountOperationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountServiceImpl accountService;

	@Test
	void getBalanceShouldCallFindById() {
		Long accountId = 1L;
		Account account = Account.builder().id(accountId).balance(new BigDecimal(1500)).build();

		when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

		accountService.getBalance(accountId);

		verify(accountRepository, only()).findById(ArgumentMatchers.eq(accountId));
	}

	@Test
	void depositShouldCallFindById() {
		Long accountId = 1L;
		Account account = Account.builder().id(accountId).balance(new BigDecimal(1500)).build();
		AccountOperationRequest request = AccountOperationRequest.builder().destinationAccountId(accountId)
				.amount(new BigDecimal(1500)).build();

		when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

		accountService.deposit(request);

		verify(accountRepository).findById(ArgumentMatchers.eq(accountId));
	}

	@Test
	void depositShouldCallSave() {
		Long accountId = 1L;
		Account account = Account.builder().id(accountId).balance(BigDecimal.ZERO).build();
		AccountOperationRequest request = AccountOperationRequest.builder().destinationAccountId(accountId)
				.amount(new BigDecimal(1500)).build();

		when(accountRepository.findById(accountId)).thenReturn(Optional.ofNullable(null));
		when(accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(account);

		accountService.deposit(request);

		verify(accountRepository).findById(ArgumentMatchers.eq(accountId));
		verify(accountRepository).save(ArgumentMatchers.any(Account.class));
	}

	@Test
	void withdrawShouldCallFindById() {
		Long accountId = 1L;
		Account account = Account.builder().id(accountId).balance(new BigDecimal(1500)).build();
		AccountOperationRequest request = AccountOperationRequest.builder().originAccountId(accountId)
				.amount(new BigDecimal(1500)).build();

		when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

		accountService.withdraw(request);

		verify(accountRepository, only()).findById(ArgumentMatchers.eq(accountId));
	}

	@Test
	void transferShouldCallFindById() {
		Long accountId = 1L;
		Account account = Account.builder().id(accountId).balance(new BigDecimal(1500)).build();
		AccountOperationRequest request = AccountOperationRequest.builder().originAccountId(accountId)
				.destinationAccountId(accountId).amount(new BigDecimal(1500)).build();

		when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

		accountService.transfer(request);

		verify(accountRepository, times(2)).findById(ArgumentMatchers.eq(accountId));
	}

	@Test
	void clearAccountsShouldCallDeleteAll() {
		accountService.clearAccounts();
		verify(accountRepository, only()).deleteAll();
	}

}
