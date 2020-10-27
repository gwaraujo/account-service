package org.gwaraujo.account.dto;

import java.math.BigDecimal;

import org.gwaraujo.account.enums.EventType;

import lombok.Data;

@Data
public class OperationRequest {

	private EventType type;
	private Long origin;
	private Long destination;
	private BigDecimal amount;

}
