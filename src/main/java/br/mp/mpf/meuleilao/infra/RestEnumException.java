package br.mp.mpf.meuleilao.infra;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.fasterxml.jackson.databind.JsonMappingException;

public enum RestEnumException {

	CONVERSION_NOT_SUPPORTED_EXCEPTION(ConversionNotSupportedException.class, HttpStatus.INTERNAL_SERVER_ERROR,
		HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
	HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION(HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE,
		HttpServletResponse.SC_NOT_ACCEPTABLE),
	HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION(HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE,
		HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE),
	HTTP_MESSAGE_NOT_READABLE_EXCEPTION(HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST,
		HttpServletResponse.SC_BAD_REQUEST),
	HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION(HttpMessageNotWritableException.class, HttpStatus.INTERNAL_SERVER_ERROR,
		HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
	HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(HttpRequestMethodNotSupportedException.class,
		HttpStatus.METHOD_NOT_ALLOWED, HttpServletResponse.SC_METHOD_NOT_ALLOWED),
	MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST,
		HttpServletResponse.SC_BAD_REQUEST),
	TYPE_MISMATCH_EXCEPTION(TypeMismatchException.class, HttpStatus.BAD_REQUEST, HttpServletResponse.SC_BAD_REQUEST),
	JSON_MAPPING_EXCEPTION(JsonMappingException.class, HttpStatus.BAD_REQUEST, HttpServletResponse.SC_BAD_REQUEST),
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST,
		HttpServletResponse.SC_BAD_REQUEST),
	BUSINESS_EXCEPTION(BusinessException.class, HttpStatus.BAD_REQUEST, HttpServletResponse.SC_BAD_REQUEST),
	EXCEPTION(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	Class<? extends Exception> exceptionClass;
	HttpStatus httpStatus;
	int errorCode;

	private RestEnumException(Class<? extends Exception> exceptionClass, HttpStatus httpStatus, int errorCode) {
		this.exceptionClass = exceptionClass;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
	}

	public Class<? extends Exception> getExceptionClass() {
		return exceptionClass;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public static RestEnumException byExceptionClass(Class<? extends Exception> exceptionClass) {
		for (RestEnumException restEnum : values()) {
			if (restEnum.exceptionClass.equals(exceptionClass)) {
				return restEnum;
			}
		}
		return EXCEPTION;
	}

}
