package com.miro.widget.repository;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.model.Widget;

import java.util.List;
import java.util.UUID;

public interface WidgetRepository {
  Widget save(WidgetRequest request);

  Widget update(UUID id, WidgetRequest request);

  Widget delete(UUID id);

  Widget find(UUID id);

  List<Widget> findAll();
}
