package com.miro.widget.repository;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WidgetRepositoryTest.TestConfiguration.class})
class WidgetRepositoryTest {

  @Autowired private WidgetRepositoryImpl widgetRepository;

  public static class TestConfiguration {
    @Bean
    public WidgetRepositoryImpl repository() {
      return new WidgetRepositoryImpl();
    }
  }

  @AfterEach
  public void clean() {
    widgetRepository.clean();
  }

  @Test
  void testSave() throws Exception {
    Widget createdWidget = createWidget();

    Widget testWidget = widgetRepository.find(createdWidget.getId());

    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZ());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
  }

  @Test
  void testUpdate() throws Exception {
    Widget createdWidget = createWidget();

    WidgetRequest request = new WidgetRequest(10, 20, 10, 50, 100);
    Widget updatedWidget = widgetRepository.update(createdWidget.getId(), request);

    Widget testWidget = widgetRepository.find(updatedWidget.getId());

    assertEquals(10, testWidget.getX());
    assertEquals(20, testWidget.getY());
    assertEquals(10, testWidget.getZ());
    assertEquals(50, testWidget.getWidth());
    assertEquals(100, testWidget.getHeight());
  }

  @Test
  void testDelete() throws Exception {
    Widget createdWidget = createWidget();

    widgetRepository.delete(createdWidget.getId());

    assertThrows(WidgetNotFoundException.class, () -> widgetRepository.find(createdWidget.getId()));
  }

  @Test
  void testFind() throws Exception {
    Widget createdWidget = createWidget();

    Widget testWidget = widgetRepository.find(createdWidget.getId());
    assertNotNull(testWidget);
  }

  @Test
  void testFindAll() {
    createWidget();

    List<Widget> widgets = widgetRepository.findAll();
    assertEquals(1, widgets.size());

    Widget testWidget = widgets.get(0);
    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZ());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
  }

  private Widget createWidget() {
    WidgetRequest request = new WidgetRequest(1, 2, 1, 5, 10);
    return widgetRepository.save(request);
  }
}
