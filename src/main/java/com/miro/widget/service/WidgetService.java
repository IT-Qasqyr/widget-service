package com.miro.widget.service;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.model.WidgetData;
import com.miro.widget.repository.WidgetRepository;
import com.miro.widget.util.WidgetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class WidgetService {

  private WidgetRepository widgetRepository;

  @Autowired
  public WidgetService(WidgetRepository widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  public Widget saveWidget(WidgetData data) {
    String id = UUID.randomUUID().toString();

    Widget widget = new Widget();
    widget.setId(id);
    widget.setX(data.getX());
    widget.setY(data.getY());
    widget.setZIndex(data.getZIndex());
    widget.setWidth(data.getWidth());
    widget.setHeight(data.getHeight());
    widget.setLastModificationDate(data.getDate());

    checkNullAndUpdateZIndex(widget);

    return widgetRepository.save(widget);
  }

  public Widget editWidget(WidgetData data) throws WidgetNotFoundException {
    Widget widget = widgetRepository.find(data.getId());
    widget.setX(data.getX());
    widget.setY(data.getY());
    widget.setZIndex(data.getZIndex());
    widget.setWidth(data.getWidth());
    widget.setHeight(data.getHeight());
    widget.setLastModificationDate(data.getDate());

    checkNullAndUpdateZIndex(widget);

    return widgetRepository.update(widget);
  }

  public void deleteWidget(WidgetData data) throws WidgetNotFoundException {
    widgetRepository.find(data.getId());
    widgetRepository.delete(data.getId());
  }

  public Widget getWidget(String id) throws WidgetNotFoundException {
    return widgetRepository.find(id);
  }

  public List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }

  private void checkNullAndUpdateZIndex(Widget widget) {
    List<Widget> widgets = widgetRepository.findAll();
    WidgetUtils.checkNullZIndex(widget, widgets);
    WidgetUtils.updateZIndex(widget, widgets);
  }
}
