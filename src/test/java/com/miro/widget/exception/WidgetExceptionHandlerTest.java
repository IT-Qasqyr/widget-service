package com.miro.widget.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WidgetExceptionHandlerTest {

  private WidgetExceptionHandler handler = new WidgetExceptionHandler();

  @Test
  void testHandleWidgetNotFoundException() {
    ExceptionResponse expected = ExceptionResponse.builder().message("Message").build();
    ExceptionResponse actual = handler.handleWidgetNotFoundException(new Exception("Message"));

    assertEquals(expected.getMessage(), actual.getMessage());
  }
}
