package org.gwaraujo.account.controller;

import java.math.BigDecimal;

import org.gwaraujo.account.dto.AccountOperationRequest;
import org.gwaraujo.account.dto.OperationRequest;
import org.gwaraujo.account.dto.OperationResponse;
import org.gwaraujo.account.enums.EventType;
import org.gwaraujo.account.exception.AccountNotFoundException;
import org.gwaraujo.account.service.AccountService;
import org.springframework.http.HttpStatus;
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

		try {
			var balance = accountService.getBalance(accountId);
			return ResponseEntity.ok(balance);
		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BigDecimal.ZERO);
		}

	}

	@PostMapping("/event")
	public ResponseEntity<?> performOperation(@RequestBody OperationRequest request) {

		OperationResponse response = null;

		try {

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

		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BigDecimal.ZERO);
		}

		return response != null ? ResponseEntity.status(HttpStatus.CREATED).body(response)
				: ResponseEntity.badRequest().build();
	}

	@PostMapping("/reset")
	public ResponseEntity<String> resetAccounts() {
		accountService.clearAccounts();
		return ResponseEntity.ok("OK");
	}

}
