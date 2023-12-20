package com.polyquimica.app.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mongodb.MongoWriteException;
import com.polyquimica.app.common.LangUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler {

	private final LangUtils langUtils;

	@ExceptionHandler(MongoWriteException.class)
	protected ResponseEntity<?> handleMongoWriteException(MongoWriteException ex) {
		Map<String, Object> response = null;
		ErrorDetails errorDetails = ErrorDetails.builder()
					.code(ErrorCode.INTERNAL_ERROR)
					.detail(ErrorCode.INTERNAL_ERROR.getDefaultMessage()).build();
		response = new GenericErrorResponse(errorDetails, null).mapOf();
		log.error(ex.toString());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		Map<String, Object> response = null;
		ErrorDetails errorDetails = ErrorDetails.builder()
					.code(ErrorCode.MESSAGE_NOT_READABLE)
					.detail(ErrorCode.MESSAGE_NOT_READABLE.getDefaultMessage()).build();
		response = new GenericErrorResponse(errorDetails, null).mapOf();
		return ResponseEntity.badRequest().body(response);
	}

	/* Throws error if request body is violating constraints on params anottated with @Valid  */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {
		Map<String, Object> response = null;
		ErrorDetails errorDetails = ErrorDetails.builder()
					.code(ErrorCode.MISSING_REQUEST_PARAMETERS)
					.detail(ErrorCode.MISSING_REQUEST_PARAMETERS.getDefaultMessage()).build();
		if (ex.getFieldErrors() != null) {
			List<FieldValidationError> fieldValidationErrors = new ArrayList<>();
			ex.getFieldErrors().forEach(fieldError -> {
				fieldValidationErrors
						.add(new FieldValidationError(fieldError.getField(), langUtils.getLocalizedMessage(fieldError.getDefaultMessage(), locale)));
			});
			response = new GenericErrorResponse(errorDetails, fieldValidationErrors).mapOf();
		}
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleException(Exception ex) {
		Map<String, Object> response = null;
		ErrorDetails errorDetails = ErrorDetails.builder()
					.code(ErrorCode.INTERNAL_ERROR)
					.detail(ErrorCode.INTERNAL_ERROR.getDefaultMessage()).build();
		response = new GenericErrorResponse(errorDetails, null).mapOf();
		log.error(ex.toString());
		return ResponseEntity.badRequest().body(response);
	}

}
