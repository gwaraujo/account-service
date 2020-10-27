package org.gwaraujo.account.service;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.vo.AccountOperationData;
import org.gwaraujo.account.dto.AccountOperationRequest;

public interface AccountService {

	BigDecimal getBalance(Long accountId);

	Account deposit(AccountOperationRequest request);

	Account withdraw(AccountOperationRequest request);

	AccountOperationData transfer(AccountOperationRequest request);

	void clearAccounts();

}
