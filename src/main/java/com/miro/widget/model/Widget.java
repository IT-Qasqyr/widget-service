package com.miro.widget.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Widget {
  private final UUID id;
  private final Integer x;
  private final Integer y;
  private Integer z;
  private final Integer width;
  private final Integer height;
  private LocalDateTime lastModificationDate;

  public Widget(UUID id, Integer x, Integer y, Integer z, Integer width, Integer height) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
    this.width = width;
    this.height = height;
    this.lastModificationDate = LocalDateTime.now();
  }

  public void setZ(Integer zIndex) {
    this.z = zIndex;
  }

  public void setLastModificationDate(LocalDateTime date) {
    this.lastModificationDate = date;
  }
}
