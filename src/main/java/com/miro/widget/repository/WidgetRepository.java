package com.miro.widget.repository;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;

import java.util.List;

public interface WidgetRepository {
  Widget save(Widget widget);

  Widget update(Widget widget) throws WidgetNotFoundException;

  void delete(String id) throws WidgetNotFoundException;

  Widget find(String id) throws WidgetNotFoundException;

  List<Widget> findAll();
}
