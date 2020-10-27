package org.gwaraujo.account.controller;

import java.math.BigDecimal;

import org.gwaraujo.account.dto.AccountOperationRequest;
import org.gwaraujo.account.dto.OperationRequest;
import org.gwaraujo.account.dto.OperationResponse;
import org.gwaraujo.account.enums.EventType;
import org.gwaraujo.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/balance")
	public ResponseEntity<BigDecimal> getBalance(@RequestParam("account_id") Long accountId) {
		var balance = accountService.getBalance(accountId);
		return ResponseEntity.ok(balance);
	}

	@PostMapping("/event")
	public ResponseEntity<OperationResponse> performOperation(@RequestBody OperationRequest request) {

		OperationResponse response = null;

		if (EventType.DEPOSIT.equals(request.getType())) {

			var accountOperationRequest = AccountOperationRequest.builder()
					.destinationAccountId(request.getDestination()).amount(request.getAmount()).build();

			var account = accountService.deposit(accountOperationRequest);
			response = OperationResponse.builder().destination(account).build();

		} else if (EventType.WITHDRAW.equals(request.getType())) {

			var accountOperationRequest = AccountOperationRequest.builder().originAccountId(request.getOrigin())
					.amount(request.getAmount()).build();

			var account = accountService.withdraw(accountOperationRequest);
			response = OperationResponse.builder().origin(account).build();

		} else if (EventType.TRANSFER.equals(request.getType())) {

			var accountOperationRequest = AccountOperationRequest.builder().originAccountId(request.getOrigin())
					.destinationAccountId(request.getDestination()).amount(request.getAmount()).build();

			var operationData = accountService.transfer(accountOperationRequest);
			response = OperationResponse.builder().origin(operationData.getOriginAccount())
					.destination(operationData.getDestinationAccount()).build();

		}

		return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
	}

	@PostMapping("/reset")
	public void resetAccounts() {
		accountService.clearAccounts();
	}

}
