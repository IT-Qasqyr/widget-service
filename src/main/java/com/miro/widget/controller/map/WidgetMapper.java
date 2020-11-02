package com.miro.widget.controller.map;

import com.miro.widget.model.Widget;
import com.miro.widget.request.WidgetRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WidgetMapper {
  @Mapping(source = "x", target = "x")
  @Mapping(source = "y", target = "y")
  @Mapping(source = "z", target = "zIndex")
  @Mapping(source = "width", target = "width")
  @Mapping(source = "height", target = "height")
  Widget requestToWidget(WidgetRequest request);
}
