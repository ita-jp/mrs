package io.nagaita.mrs.domain;

public class AlreadyReservedException extends RuntimeException {

	public AlreadyReservedException(String message) {
		super(message);
	}

}
