package com.miro.widget.util;

import com.miro.widget.model.Widget;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WidgetUtilsTest {

  @Test
  void testUpdateZIndexShiftWidgets() {
    Widget widget = new Widget();
    widget.setZIndex(0);

    Widget widgetWithZeroIndex = new Widget();
    widgetWithZeroIndex.setZIndex(0);

    Widget widgetWithOneIndex = new Widget();
    widgetWithOneIndex.setZIndex(1);

    ArrayList<Widget> widgets = new ArrayList<>();
    widgets.add(widgetWithZeroIndex);
    widgets.add(widgetWithOneIndex);

    WidgetUtils.updateZIndex(widget, widgets);

    assertEquals(widget.getZIndex(), 0);
    assertEquals(widgetWithZeroIndex.getZIndex(), 1);
    assertEquals(widgetWithOneIndex.getZIndex(), 2);
  }

  @Test
  void testUpdateZIndexUnchangedWidgets() {
    Widget widget = new Widget();
    widget.setZIndex(3);

    Widget widgetWithZeroIndex = new Widget();
    widgetWithZeroIndex.setZIndex(0);

    Widget widgetWithOneIndex = new Widget();
    widgetWithOneIndex.setZIndex(1);

    ArrayList<Widget> widgets = new ArrayList<>();
    widgets.add(widgetWithZeroIndex);
    widgets.add(widgetWithOneIndex);

    WidgetUtils.updateZIndex(widget, widgets);

    assertEquals(widget.getZIndex(), 3);
    assertEquals(widgetWithZeroIndex.getZIndex(), 0);
    assertEquals(widgetWithOneIndex.getZIndex(), 1);
  }
}
