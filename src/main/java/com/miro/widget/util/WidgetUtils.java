package com.miro.widget.util;

import com.miro.widget.model.Widget;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WidgetUtils {

  public static List<Widget> updateZIndex(Widget widget, List<Widget> widgets) {
    if (widgets.stream().anyMatch(widgetFiltered -> widgetFiltered.getZ().equals(widget.getZ()))) {
      List<Widget> shiftedWidgets =
          widgets.stream().filter(w -> w.getZ() >= widget.getZ()).collect(Collectors.toList());

      shiftedWidgets.forEach(
          w -> {
            w.setZ(w.getZ() + 1);
            w.setLastModificationDate(LocalDateTime.now());
          });
      return shiftedWidgets;
    } else {
      return Collections.emptyList();
    }
  }

  public static void checkNullZIndex(Widget widget, List<Widget> widgets) {
    if (widget.getZ() == null) {
      int computedZIndex = 0;
      Optional<Widget> max = widgets.stream().max(Comparator.comparingInt(Widget::getZ));
      if (max.isPresent()) {
        computedZIndex = max.get().getZ() + 1;
      }
      widget.setZ(computedZIndex);
    }
  }
}
