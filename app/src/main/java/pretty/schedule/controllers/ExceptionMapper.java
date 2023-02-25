package pretty.schedule.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pretty.schedule.scheme.ErrorResponse;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {
  @Value("${application.debug:false}")
  private boolean debug;
  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapper.class);

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
    LOGGER.error("Unexpected exception", ex);

    ErrorResponse errorResponse = new ErrorResponse();
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    errorResponse.setMessage(debug ? ex.getMessage() : "An error occurred");

    if (debug) {
      StringWriter sw = new StringWriter();
      ex.printStackTrace(new PrintWriter(sw));
      errorResponse.setDetails(sw.toString());
    }

    return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
  }
}
