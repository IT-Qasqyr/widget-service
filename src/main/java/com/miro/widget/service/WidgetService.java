package com.miro.widget.service;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.model.Widget;

import java.util.List;
import java.util.UUID;

public interface WidgetService {
  Widget saveWidget(WidgetRequest request);

  Widget editWidget(UUID id, WidgetRequest request);

  void deleteWidget(UUID id);

  Widget getWidget(UUID id);

  List<Widget> getAllWidgets();
}
