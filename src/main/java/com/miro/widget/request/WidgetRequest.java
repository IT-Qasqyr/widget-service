package com.miro.widget.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class WidgetRequest {
  @NotNull private Integer x;
  @NotNull private Integer y;

  private Integer zIndex;

  @NotNull @Positive private Integer width;
  @NotNull @Positive private Integer height;
}
