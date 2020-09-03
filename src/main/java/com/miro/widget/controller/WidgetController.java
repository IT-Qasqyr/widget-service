package com.miro.widget.controller;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.model.Widget;
import com.miro.widget.service.WidgetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/widget")
public class WidgetController {

  private final WidgetServiceImpl widgetService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Widget createWidget(@Valid @RequestBody WidgetRequest request) {
    return widgetService.saveWidget(request);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public Widget editWidget(@PathVariable UUID id, @Valid @RequestBody WidgetRequest request) {
    return widgetService.editWidget(id, request);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteWidget(@PathVariable UUID id) {
    widgetService.deleteWidget(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public Widget getWidget(@PathVariable UUID id) {
    return widgetService.getWidget(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<Widget> getAllWidgets() {
    return widgetService.getAllWidgets();
  }
}
