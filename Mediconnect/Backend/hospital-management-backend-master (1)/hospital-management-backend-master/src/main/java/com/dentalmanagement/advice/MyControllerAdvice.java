package com.dentalmanagement.advice;

import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.dentalmanagement.exception.AppointmentNotFoundException;
import com.dentalmanagement.exception.UserNotFoundException;



@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>("User Not Found, please send proper data", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<String> handleAppointmentNotFoundException(AppointmentNotFoundException appointmentNotFoundException) {
		return new ResponseEntity<String>("Appointment Not Found, please send proper data", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		return new ResponseEntity<String>("No Value is present in DB, Please change your request", HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Please change your Http Method type", HttpStatus.BAD_REQUEST);
	}

}
