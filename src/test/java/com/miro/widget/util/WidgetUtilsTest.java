package com.miro.widget.util;

import com.miro.widget.model.Widget;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WidgetUtilsTest {

  @Test
  void testUpdateZIndexShiftWidgets() {
    Widget widget = widgetWithZIndex(0);

    Widget widgetWithZeroIndex = widgetWithZIndex(0);
    Widget widgetWithOneIndex = widgetWithZIndex(1);

    ArrayList<Widget> widgets = new ArrayList<>();
    widgets.add(widgetWithZeroIndex);
    widgets.add(widgetWithOneIndex);

    WidgetUtils.updateZIndex(widget, widgets);

    assertEquals(widget.getZ(), 0);
    assertEquals(widgetWithZeroIndex.getZ(), 1);
    assertEquals(widgetWithOneIndex.getZ(), 2);
  }

  @Test
  void testUpdateZIndexUnchangedWidgets() {
    Widget widget = widgetWithZIndex(3);

    Widget widgetWithZeroIndex = widgetWithZIndex(0);
    Widget widgetWithOneIndex = widgetWithZIndex(1);

    ArrayList<Widget> widgets = new ArrayList<>();
    widgets.add(widgetWithZeroIndex);
    widgets.add(widgetWithOneIndex);

    WidgetUtils.updateZIndex(widget, widgets);

    assertEquals(widget.getZ(), 3);
    assertEquals(widgetWithZeroIndex.getZ(), 0);
    assertEquals(widgetWithOneIndex.getZ(), 1);
  }

  private Widget widgetWithZIndex(Integer zIndex) {
    return new Widget(UUID.randomUUID(), 1, 1, zIndex, 1, 1);
  }
}
