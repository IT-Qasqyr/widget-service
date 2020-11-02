package com.miro.widget.service;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;

import java.util.List;

public interface WidgetService {
  Widget saveWidget(Widget widget);

  Widget editWidget(Widget widget) throws WidgetNotFoundException;

  void deleteWidget(String id) throws WidgetNotFoundException;

  Widget getWidget(String id) throws WidgetNotFoundException;

  List<Widget> getAllWidgets();
}
