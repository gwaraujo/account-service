package org.gwaraujo.account.enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {

	DEPOSIT, WITHDRAW, TRANSFER;

	@JsonCreator
	public static EventType decode(final String typeName) {
		return Stream.of(EventType.values()).filter(e -> e.name().equalsIgnoreCase(typeName)).findFirst().orElse(null);
	}

}
