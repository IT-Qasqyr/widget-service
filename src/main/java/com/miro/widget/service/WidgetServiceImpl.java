package com.miro.widget.service;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.model.Widget;
import com.miro.widget.repository.WidgetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WidgetServiceImpl implements WidgetService {

  private final WidgetRepositoryImpl widgetRepository;

  @Autowired
  public WidgetServiceImpl(WidgetRepositoryImpl widgetRepository) {
    this.widgetRepository = widgetRepository;
  }

  public synchronized Widget saveWidget(WidgetRequest request) {
    return widgetRepository.save(request);
  }

  public synchronized Widget editWidget(UUID id, WidgetRequest request) {
    return widgetRepository.update(id, request);
  }

  public synchronized void deleteWidget(UUID id) {
    widgetRepository.delete(id);
  }

  public synchronized Widget getWidget(UUID id) {
    return widgetRepository.find(id);
  }

  public synchronized List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }
}
