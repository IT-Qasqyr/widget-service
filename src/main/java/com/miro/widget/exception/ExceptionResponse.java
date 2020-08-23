package com.miro.widget.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {
  private Integer code;
  private String message;
}
