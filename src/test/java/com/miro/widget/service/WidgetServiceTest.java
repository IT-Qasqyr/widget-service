package com.miro.widget.service;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.repository.WidgetRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WidgetServiceTest {

  private final UUID testId = UUID.randomUUID();

  @Mock private WidgetRepositoryImpl widgetRepository;

  private WidgetServiceImpl widgetService;

  @BeforeEach
  public void init() {
    widgetService = new WidgetServiceImpl(widgetRepository);
  }

  @Test
  void saveWidget() {
    WidgetRequest request = new WidgetRequest(1, 2, 1, 5, 10);
    widgetService.saveWidget(request);

    ArgumentCaptor<WidgetRequest> argCap = ArgumentCaptor.forClass(WidgetRequest.class);
    verify(widgetRepository).save(argCap.capture());

    WidgetRequest testRequest = argCap.getValue();

    assertEquals(1, testRequest.getX());
    assertEquals(2, testRequest.getY());
    assertEquals(1, testRequest.getZ());
    assertEquals(5, testRequest.getWidth());
    assertEquals(10, testRequest.getHeight());
  }

  @Test
  void editWidget() throws Exception {
    WidgetRequest request = new WidgetRequest(1, 3, 1, 5, 10);
    widgetService.editWidget(testId, request);

    ArgumentCaptor<WidgetRequest> argCap = ArgumentCaptor.forClass(WidgetRequest.class);
    verify(widgetRepository).update(eq(testId), argCap.capture());

    WidgetRequest testRequest = argCap.getValue();

    assertEquals(1, testRequest.getX());
    assertEquals(3, testRequest.getY());
    assertEquals(1, testRequest.getZ());
    assertEquals(5, testRequest.getWidth());
    assertEquals(10, testRequest.getHeight());
  }

  @Test
  void deleteWidget() throws Exception {
    widgetService.deleteWidget(testId);
    verify(widgetRepository).delete(eq(testId));
  }

  @Test
  void getWidget() throws Exception {
    widgetService.getWidget(testId);
    verify(widgetRepository).find(eq(testId));
  }

  @Test
  void getAllWidgets() {
    when(widgetRepository.findAll()).thenReturn(Collections.emptyList());
    widgetService.getAllWidgets();

    verify(widgetRepository).findAll();
  }
}
