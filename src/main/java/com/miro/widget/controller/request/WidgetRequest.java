package com.miro.widget.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class WidgetRequest {
  @NotNull private Integer x;
  @NotNull private Integer y;

  private Integer z;

  @NotNull @Positive private Integer width;
  @NotNull @Positive private Integer height;
}
