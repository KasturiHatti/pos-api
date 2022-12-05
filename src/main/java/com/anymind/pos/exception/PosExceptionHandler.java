package com.anymind.pos.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class PosExceptionHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler(InvalidInputException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidInputException ex) {
		return handleBadRequest(ex);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMissingRequestBody(HttpMessageNotReadableException ex) {

		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInvalidRequestBody(HttpMessageConversionException ex) {
		return handleBadRequest(ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInvalidRequestBody(MethodArgumentNotValidException ex) {
		return handleBadRequest(ex.getBindingResult().getFieldErrors());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMissingRequestParam(MissingServletRequestParameterException ex) {
		return handleBadRequest(ex);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<ErrorResponse> handleServletRequestBindingException(ServletRequestBindingException ex) {
		return handleBadRequest(ex);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		return handleBadRequest(ex);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		ErrorResponse errResponse = ErrorResponse.builder()
			.message(ex.getMessage())
			.build();
		return new ResponseEntity<>(errResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
		ErrorResponse errResponse = ErrorResponse.builder()
			.message(ex.getMessage())
			.build();
		return new ResponseEntity<>(errResponse, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> handleBadRequest(List<FieldError> fieldErrorList) {
		List<ErrorResponse> errorMessageList = new ArrayList<>();

		for (FieldError fieldError : fieldErrorList) {
			String message = fieldError.getObjectName() + "." + fieldError.getField() + ": " + fieldError.getDefaultMessage();
			errorMessageList.add(new ErrorResponse(message));
		}

		return new ResponseEntity<>(errorMessageList.get(0), HttpStatus.BAD_REQUEST);
	}
}
