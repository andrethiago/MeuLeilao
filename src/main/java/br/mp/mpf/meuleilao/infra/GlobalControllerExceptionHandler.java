package br.mp.mpf.meuleilao.infra;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<RestExceptionInfo> defaultErrorHandler(HttpServletRequest request,
		HttpServletResponse response, Exception e) throws Exception {

		RestExceptionInfo restExceptionInfo = handleRestException(e, request);
		response.setStatus(restExceptionInfo.getErrorCode());

		return new ResponseEntity<>(restExceptionInfo, restExceptionInfo.getHttpStatus());
	}

	private RestExceptionInfo handleRestException(Exception ex, HttpServletRequest request) {
		RestEnumException restEnumException = RestEnumException.byExceptionClass(ex.getClass());

		ex.printStackTrace();

		String message = "";
		HttpStatus httpStatus = restEnumException.httpStatus;
		int errorCode = restEnumException.errorCode;

		if (ex instanceof ConversionNotSupportedException) {
			message = "Problemas ao converver a resposta para JSON.";
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			message = "Erro ao gerar resposta aceitável para o cliente.";
		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
			message = "Conteúdo " + ((HttpMediaTypeNotSupportedException) ex).getContentType().toString()
				+ " não suportado. Envie via: " + ((HttpMediaTypeNotSupportedException) ex).getSupportedMediaTypes();
		} else if (ex instanceof HttpMessageNotReadableException) {
			message = "Erro ao ler objeto da requisição.";
		} else if (ex instanceof HttpMessageNotWritableException) {
			message = "Problemas ao converver a resposta para JSON.";
		} else if (ex instanceof HttpRequestMethodNotSupportedException) {
			String toFormat = "O método %s não é suportado para a url %s.\n Métodos suportados: %s";
			HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) ex;
			StringBuilder builder = new StringBuilder();
			for (String method : exception.getSupportedMethods()) {
				builder.append(method + " ");
			}
			message = String.format(toFormat, exception.getMethod(), request.getRequestURL(), builder.toString());

		} else if (ex instanceof MissingServletRequestParameterException) {
			message = "Verifique os parâmetros de sua requisição. Algum parâmetro obrigatório está faltando.";
		} else if (ex instanceof TypeMismatchException) {
			message = "Verifique sua url. O endereço não está mapeado.";

		} else if (ex instanceof JsonMappingException) {
			final Pattern pattern = Pattern.compile("Unknown property '(.+?)'");
			final Matcher matcher = pattern.matcher(ex.getMessage());
			if (matcher.find()) {
				message = "Propriedade inexistente: " + matcher.group(1);
			} else {
				message = "Propriedade inexistente. Verifique os campos fl.";
			}
		} else if (ex instanceof MethodArgumentNotValidException) {
			BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
			StringBuilder builder = new StringBuilder();
			builder.append("Objeto enviado no formato inválido ou com campos ausentes: [\r\n");
			for (ObjectError error : bindingResult.getAllErrors()) {
				builder.append(error.getDefaultMessage());
				builder.append("\r\n");
			}
			builder.append("]");
			message = builder.toString();
		} else if (ex instanceof BusinessException) {
			message = ex.getMessage();
		} else {
			message = "Erro inesperado. Tente novamente mais tarde.";
		}

		return new RestExceptionInfo(message, httpStatus, errorCode, ex);
	}

}
