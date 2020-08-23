package com.miro.widget.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WidgetExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({WidgetNotFoundException.class})
  @ResponseBody
  public ExceptionResponse handleWidgetNotFoundException(Exception ex) {
    log.warn(ex.getMessage());
    return ExceptionResponse.builder().message(ex.getMessage()).build();
  }
}
