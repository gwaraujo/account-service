package org.gwaraujo.account.domain.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private BigDecimal balance;

}
