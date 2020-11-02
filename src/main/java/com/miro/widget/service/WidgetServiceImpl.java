package com.miro.widget.service;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.repository.WidgetRepositoryImpl;
import com.miro.widget.util.WidgetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetServiceImpl implements WidgetService {

  private final WidgetRepositoryImpl widgetRepository;

  @Autowired
  public WidgetServiceImpl(WidgetRepositoryImpl widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  public Widget saveWidget(Widget widget) {
    checkNullAndUpdateZIndex(widget);
    return widgetRepository.save(widget);
  }

  public Widget editWidget(Widget widget) throws WidgetNotFoundException {
    checkNullAndUpdateZIndex(widget);
    return widgetRepository.update(widget);
  }

  public void deleteWidget(String id) throws WidgetNotFoundException {
    widgetRepository.delete(id);
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
