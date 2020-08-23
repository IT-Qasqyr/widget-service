package com.miro.widget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WidgetNotFoundException extends Exception {
  public WidgetNotFoundException(final String msg) {
    super(msg);
  }
}
