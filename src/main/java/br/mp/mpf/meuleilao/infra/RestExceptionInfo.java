package br.mp.mpf.meuleilao.infra;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

/**
 * Encapsula informações relativas aos erros que devem ser retornados para o cliente.
 * 
 *
 */
public class RestExceptionInfo {

	String message;

	HttpStatus httpStatus;

	int errorCode;

	String exceptionMessage;

	public RestExceptionInfo(String message, HttpStatus httpStatus, int errorCode) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
	}

	public RestExceptionInfo(String message, HttpStatus httpStatus, int errorCode, Throwable throwable) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.exceptionMessage = ExceptionUtils.getStackTrace(throwable);
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
