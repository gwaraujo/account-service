package org.gwaraujo.account.domain.vo;

import org.gwaraujo.account.domain.entity.Account;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountOperationData {

	private Account originAccount;
	private Account destinationAccount;

}
