package com.miro.widget.controller;

import com.miro.widget.controller.map.WidgetMapper;
import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.request.WidgetRequest;
import com.miro.widget.service.WidgetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/widget")
public class WidgetController {

  private final WidgetMapper mapper;

  private final WidgetServiceImpl widgetService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Widget createWidget(@Valid @RequestBody WidgetRequest widgetRequest) {
    return widgetService.saveWidget(mapper.requestToWidget(widgetRequest));
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public void editWidget(@PathVariable String id, @Valid @RequestBody WidgetRequest widgetRequest)
      throws WidgetNotFoundException {
    widgetService.editWidget(mapper.requestToWidget(widgetRequest));
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteWidget(@PathVariable String id) throws WidgetNotFoundException {
    widgetService.deleteWidget(id);
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
