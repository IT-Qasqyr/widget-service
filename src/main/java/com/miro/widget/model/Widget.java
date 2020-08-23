package com.miro.widget.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.function.Consumer;

@Data
public class Widget {
  private String id;
  private Integer x;
  private Integer y;

  @JsonProperty("z-index")
  private Integer zIndex;

  private Integer width;
  private Integer height;
  private Date lastModificationDate;
}
