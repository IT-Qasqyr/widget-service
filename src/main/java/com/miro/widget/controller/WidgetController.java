package com.miro.widget.controller;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.model.WidgetData;
import com.miro.widget.request.WidgetRequest;
import com.miro.widget.service.WidgetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/widget")
public class WidgetController {

  private WidgetService widgetService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Widget createWidget(@Valid @RequestBody WidgetRequest widgetRequest) {
    return widgetService.saveWidget(
        WidgetData.builder()
            .x(widgetRequest.getX())
            .y(widgetRequest.getY())
            .zIndex(widgetRequest.getZIndex())
            .width(widgetRequest.getWidth())
            .height(widgetRequest.getHeight())
            .date(new Date())
            .build());
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public void editWidget(@PathVariable String id, @Valid @RequestBody WidgetRequest widgetRequest)
      throws WidgetNotFoundException {
    widgetService.editWidget(
        WidgetData.builder()
            .id(id)
            .x(widgetRequest.getX())
            .y(widgetRequest.getY())
            .zIndex(widgetRequest.getZIndex())
            .width(widgetRequest.getWidth())
            .height(widgetRequest.getHeight())
            .date(new Date())
            .build());
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteWidget(@PathVariable String id) throws WidgetNotFoundException {
    widgetService.deleteWidget(WidgetData.builder().id(id).date(new Date()).build());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public Widget getWidget(@PathVariable String id) throws WidgetNotFoundException {
    return widgetService.getWidget(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<Widget> getAllWidgets() {
    return widgetService.getAllWidgets();
  }
}
