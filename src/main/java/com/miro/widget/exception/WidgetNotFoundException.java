package com.miro.widget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WidgetNotFoundException extends Exception {
  public WidgetNotFoundException(final UUID id) {
    super("Widget is not found with id = " + id);
  }
}
