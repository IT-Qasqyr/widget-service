package com.miro.widget.util;

import com.miro.widget.model.Widget;

import java.util.*;
import java.util.stream.Collectors;

public class WidgetUtils {

  public static List<Widget> updateZIndex(Widget widget, List<Widget> widgets) {
    if (widgets.stream()
        .anyMatch(widgetFiltered -> widgetFiltered.getZIndex().equals(widget.getZIndex()))) {
      List<Widget> shiftedWidgets =
          widgets.stream()
              .filter(w -> w.getZIndex() >= widget.getZIndex())
              .collect(Collectors.toList());

      shiftedWidgets.forEach(
          w -> {
            w.setZIndex(w.getZIndex() + 1);
            w.setLastModificationDate(new Date());
          });
      return shiftedWidgets;
    } else {
      return Collections.emptyList();
    }
  }

  public static void checkNullZIndex(Widget widget, List<Widget> widgets) {
    if (widget.getZIndex() == null) {
      int computedZIndex = 0;
      Optional<Widget> max = widgets.stream().max(Comparator.comparingInt(Widget::getZIndex));
      if (max.isPresent()) {
        computedZIndex = max.get().getZIndex() + 1;
      }
      widget.setZIndex(computedZIndex);
    }
  }
}
