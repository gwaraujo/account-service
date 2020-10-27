package org.gwaraujo.account.service;

import java.math.BigDecimal;

import org.gwaraujo.account.domain.entity.Account;
import org.gwaraujo.account.domain.vo.AccountOperationData;
import org.gwaraujo.account.dto.OperationRequest;

public interface AccountService {

	BigDecimal getBalance(Long accountId);

	Account deposit(OperationRequest request);

	Account withdraw(OperationRequest request);

	AccountOperationData transfer(OperationRequest request);

	void clearAccounts();

}
