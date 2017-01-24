package com.foresee.springapp;

/**
 * 
 * @author abdel.dridi
 *
 */
// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Request")
public class RequestNotfoundException extends RuntimeException {

	private static final long serialVersionUID = -1055301007451748746L;

	public RequestNotfoundException() {
		super();
	}

	public RequestNotfoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequestNotfoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestNotfoundException(String message) {
		super(message);
	}

	public RequestNotfoundException(Throwable cause) {
		super(cause);
	}

}
