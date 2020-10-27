package org.gwaraujo.account.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OperationRequest {

	private Long originAccountId;
	private Long destinationAccountId;
	private BigDecimal amount;

}
