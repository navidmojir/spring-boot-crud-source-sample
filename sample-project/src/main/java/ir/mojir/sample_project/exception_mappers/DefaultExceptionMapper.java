package ir.mojir.sample_project.exception_mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ir.mojir.spring_boot_commons.dtos.ErrorDto;
import ir.mojir.spring_boot_commons.enums.ErrorEnum;
import ir.mojir.spring_boot_commons.exceptions.ServiceException;

@ControllerAdvice
public class DefaultExceptionMapper {
	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionMapper.class);
	
	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<Object> exception(ServiceException e) {
		logger.error("An exception occured in processing request. Trace: ", e);
		return new ResponseEntity<>(e.getError(), e.getHttpStatus());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		logger.error("A general exception occured in processing request. Trace: ", e);
		return new ResponseEntity<>(new ErrorDto("A general error occured. Please contact administrator.", ErrorEnum.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
