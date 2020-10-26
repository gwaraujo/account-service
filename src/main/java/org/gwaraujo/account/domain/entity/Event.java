package org.gwaraujo.account.domain.entity;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Event {

	private EventType type;
	private Account originAccount;
	private Account destinationAccount;
	private BigDecimal amount;

}
