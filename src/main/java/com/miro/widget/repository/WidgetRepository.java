package com.miro.widget.repository;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {

  private Map<String, Widget> widgets = new HashMap<>();

  public Widget save(Widget widget) {
    widgets.put(widget.getId(), widget);
    return widget;
  }

  public Widget update(Widget widget) {
    widgets.replace(widget.getId(), widget);
    return widget;
  }

  public void delete(String id) {
    widgets.remove(id);
  }

  public Widget find(String id) throws WidgetNotFoundException {
    if (!widgets.containsKey(id)) {
      throw new WidgetNotFoundException(String.format("No widgetId = %s was found", id));
    }
    return widgets.get(id);
  }

  public List<Widget> findAll() {
    return widgets.values().stream().sorted(sortByZIndex()).collect(Collectors.toList());
  }

  private Comparator<Widget> sortByZIndex() {
    return Comparator.comparing(Widget::getZIndex);
  }

  public void clean() {
    widgets.clear();
  }
}
