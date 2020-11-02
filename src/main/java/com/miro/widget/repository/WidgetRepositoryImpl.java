package com.miro.widget.repository;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {

  private final Map<String, Widget> widgets = new HashMap<>();

  public synchronized Widget save(Widget widget) {
    String id = UUID.randomUUID().toString();
    widget.setId(id);

    widgets.put(widget.getId(), widget);
    return widget;
  }

  public synchronized Widget update(Widget widget) throws WidgetNotFoundException {
    find(widget.getId());

    widgets.replace(widget.getId(), widget);
    return widget;
  }

  public synchronized void delete(String id) throws WidgetNotFoundException {
    find(id);

    widgets.remove(id);
  }

  public synchronized Widget find(String id) throws WidgetNotFoundException {
    if (!widgets.containsKey(id)) {
      throw new WidgetNotFoundException(String.format("No widgetId = %s was found", id));
    }
    return widgets.get(id);
  }

  public synchronized List<Widget> findAll() {
    return widgets.values().stream().sorted(sortByZIndex()).collect(Collectors.toList());
  }

  public synchronized void clean() {
    widgets.clear();
  }

  private Comparator<Widget> sortByZIndex() {
    return Comparator.comparing(Widget::getZIndex);
  }
}
