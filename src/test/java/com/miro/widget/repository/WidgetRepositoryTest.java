package com.miro.widget.repository;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
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

  @BeforeEach
  public void init() {
    Widget widget = new Widget();
    widget.setId("widget2");
    widget.setX(1);
    widget.setY(2);
    widget.setZIndex(1);
    widget.setWidth(5);
    widget.setHeight(10);
    widget.setLastModificationDate(new Date());
    widgetRepository.save(widget);
  }

  @AfterEach
  public void clean() {
    widgetRepository.clean();
  }

  @Test
  void testSave() throws WidgetNotFoundException {
    Widget widget = new Widget();
    widget.setId("widget1");
    widget.setX(1);
    widget.setY(2);
    widget.setZIndex(1);
    widget.setWidth(5);
    widget.setHeight(10);
    widget.setLastModificationDate(new Date());

    widgetRepository.save(widget);

    Widget testWidget = widgetRepository.find("widget1");
    assertEquals("widget1", testWidget.getId());
    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZIndex());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
  }

  @Test
  void testUpdate() throws WidgetNotFoundException {
    Widget widget = new Widget();
    widget.setId("widget2");
    widget.setX(10);
    widget.setY(20);
    widget.setZIndex(10);
    widget.setWidth(50);
    widget.setHeight(100);
    widget.setLastModificationDate(new Date());

    widgetRepository.update(widget);

    Widget testWidget = widgetRepository.find("widget2");
    assertEquals("widget2", testWidget.getId());
    assertEquals(10, testWidget.getX());
    assertEquals(20, testWidget.getY());
    assertEquals(10, testWidget.getZIndex());
    assertEquals(50, testWidget.getWidth());
    assertEquals(100, testWidget.getHeight());
  }

  @Test
  void testDelete() throws WidgetNotFoundException {
    widgetRepository.delete("widget2");
    assertThrows(WidgetNotFoundException.class, () -> widgetRepository.find("widget2"));
  }

  @Test
  void testFind() throws WidgetNotFoundException {
    Widget testWidget = widgetRepository.find("widget2");

    assertEquals("widget2", testWidget.getId());
    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZIndex());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
  }

  @Test
  void testFindAll() {
    List<Widget> widgets = widgetRepository.findAll();

    assertNotNull(widgets);
    assertEquals(1, widgets.size());

    Widget testWidget = widgets.get(0);
    assertEquals("widget2", testWidget.getId());
    assertEquals(1, testWidget.getX());
    assertEquals(2, testWidget.getY());
    assertEquals(1, testWidget.getZIndex());
    assertEquals(5, testWidget.getWidth());
    assertEquals(10, testWidget.getHeight());
  }
}
