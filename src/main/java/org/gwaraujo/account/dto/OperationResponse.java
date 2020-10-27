package org.gwaraujo.account.dto;

import org.gwaraujo.account.domain.entity.Account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OperationResponse {

	@JsonInclude(Include.NON_NULL)
	private Account origin;

	@JsonInclude(Include.NON_NULL)
	private Account destination;

}
