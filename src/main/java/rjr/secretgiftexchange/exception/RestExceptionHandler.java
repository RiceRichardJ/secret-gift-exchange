package rjr.secretgiftexchange.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e) {
		return ResponseEntity.notFound().build();
	}

}
