package org.gwaraujo.account.domain.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

	private Long id;
	private BigDecimal balance;

}
