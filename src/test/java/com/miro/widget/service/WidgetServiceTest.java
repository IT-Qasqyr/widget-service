package com.miro.widget.service;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import com.miro.widget.repository.WidgetRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WidgetServiceTest {

  @Mock private WidgetRepositoryImpl widgetRepository;

  private WidgetServiceImpl widgetService;
  private Widget widget;

  @BeforeEach
  public void init() {
    widgetService = new WidgetServiceImpl(widgetRepository);

    widget = new Widget();
    widget.setId("widget1");
  }

  @Test
  void saveWidget() {
    Widget widget = new Widget();
    widget.setX(1);
    widget.setY(2);
    widget.setZIndex(1);
    widget.setWidth(5);
    widget.setHeight(10);
    widget.setLastModificationDate(new Date());

    widgetService.saveWidget(widget);

    ArgumentCaptor<Widget> argCap = ArgumentCaptor.forClass(Widget.class);

    verify(widgetRepository).save(argCap.capture());

    Widget testWidget = argCap.getValue();

    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZIndex());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
    assertNotNull(testWidget.getLastModificationDate());
  }

  @Test
  void editWidget() throws WidgetNotFoundException {
    Widget widget = new Widget();
    widget.setId("widget1");
    widget.setX(1);
    widget.setY(3);
    widget.setZIndex(1);
    widget.setWidth(5);
    widget.setHeight(10);
    widget.setLastModificationDate(new Date());

    when(widgetRepository.find(eq("widget1"))).thenReturn(widget);
    widgetService.editWidget(widget);

    ArgumentCaptor<Widget> argCap = ArgumentCaptor.forClass(Widget.class);

    verify(widgetRepository).update(argCap.capture());

    Widget testWidget = argCap.getValue();

    assertEquals("widget1", testWidget.getId());
    assertEquals(1, testWidget.getX());
    assertEquals(3, testWidget.getY());
    assertEquals(1, testWidget.getZIndex());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
    assertNotNull(testWidget.getLastModificationDate());
  }

  @Test
  void deleteWidget() throws WidgetNotFoundException {
    when(widgetRepository.find(eq("widget1"))).thenReturn(widget);
    widgetService.deleteWidget("widget1");

    verify(widgetRepository).delete(eq("widget1"));
  }

  @Test
  void getWidget() throws WidgetNotFoundException {
    widgetService.getWidget("widget1");

    verify(widgetRepository).find(eq("widget1"));
  }

  @Test
  void getAllWidgets() {
    when(widgetRepository.findAll()).thenReturn(List.of(widget));
    widgetService.getAllWidgets();

    verify(widgetRepository).findAll();
  }
}
