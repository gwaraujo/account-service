package org.gwaraujo.account.service;

import java.math.BigDecimal;

import org.gwaraujo.account.business.CreateAccount;
import org.gwaraujo.account.business.DepositAccountAmount;
import org.gwaraujo.account.business.GetAccountBalance;
import org.gwaraujo.account.business.WithdrawAccountAmount;
import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.repository.AccountRepository;
import org.gwaraujo.account.domain.vo.AccountOperationData;
import org.gwaraujo.account.dto.AccountOperationRequest;
import org.gwaraujo.account.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public BigDecimal getBalance(Long accountId) {
		var account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

		return new GetAccountBalance().getBalance(account);
	}

	@Override
	public Account deposit(AccountOperationRequest request) {
		var account = accountRepository.findById(request.getDestinationAccountId())
				.orElse(createNewAccount(request.getDestinationAccountId()));

		return new DepositAccountAmount().deposit(account, request.getAmount());
	}

	@Override
	public Account withdraw(AccountOperationRequest request) {
		var account = accountRepository.findById(request.getOriginAccountId())
				.orElseThrow(AccountNotFoundException::new);

		return new WithdrawAccountAmount().withdraw(account, request.getAmount());
	}

	@Override
	public AccountOperationData transfer(AccountOperationRequest request) {
		var originAccount = accountRepository.findById(request.getOriginAccountId())
				.orElseThrow(AccountNotFoundException::new);

		var destinationAccount = accountRepository.findById(request.getDestinationAccountId())
				.orElse(createNewAccount(request.getDestinationAccountId()));

		originAccount = new WithdrawAccountAmount().withdraw(originAccount, request.getAmount());
		destinationAccount = new DepositAccountAmount().deposit(destinationAccount, request.getAmount());

		return AccountOperationData.builder().originAccount(originAccount).destinationAccount(destinationAccount)
				.build();
	}

	@Override
	public void clearAccounts() {
		accountRepository.deleteAll();
	}

	private Account createNewAccount(Long accountId) {
		Account account = new CreateAccount().create(accountId);
		return accountRepository.save(account);
	}

}
