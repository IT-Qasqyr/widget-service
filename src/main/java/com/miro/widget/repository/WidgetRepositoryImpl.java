package com.miro.widget.repository;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.util.WidgetUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WidgetRepositoryImpl implements WidgetRepository {

  private final List<Widget> widgets = Collections.synchronizedList(new ArrayList<>());

  public Widget save(WidgetRequest request) {
    Widget widget =
        new Widget(
            UUID.randomUUID(),
            request.getX(),
            request.getY(),
            request.getZ(),
            request.getWidth(),
            request.getHeight());

    checkNullAndUpdateZIndex(widget);
    widgets.add(widget);

    return widget;
  }

  public Widget update(UUID id, WidgetRequest request) {
    delete(id);

    Widget widget =
        new Widget(
            id,
            request.getX(),
            request.getY(),
            request.getZ(),
            request.getWidth(),
            request.getHeight());

    checkNullAndUpdateZIndex(widget);
    widgets.add(widget);

    return widget;
  }

  public Widget delete(UUID id) {
    Widget widget = find(id);

    widgets.removeIf(w -> w.getId().equals(id));

    return widget;
  }

  @SneakyThrows
  public Widget find(UUID id) {
    return widgets.stream()
        .filter(w -> w.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new WidgetNotFoundException(id));
  }

  public List<Widget> findAll() {
    return widgets.stream().sorted(sortByZIndex()).collect(Collectors.toList());
  }

  public void clean() {
    widgets.clear();
  }

  private void checkNullAndUpdateZIndex(Widget widget) {
    List<Widget> widgets = findAll();
    WidgetUtils.checkNullZIndex(widget, widgets);
    WidgetUtils.updateZIndex(widget, widgets);
  }

  private Comparator<Widget> sortByZIndex() {
    return Comparator.comparing(Widget::getZ);
  }
}
