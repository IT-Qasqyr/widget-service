package com.miro.widget.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class WidgetData {
  private String id;
  private Integer x;
  private Integer y;
  private Integer zIndex;
  private Integer width;
  private Integer height;
  private Date date;
}
