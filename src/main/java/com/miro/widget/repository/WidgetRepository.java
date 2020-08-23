package com.miro.widget.repository;

import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.model.Widget;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {

  private Map<String, Widget> widgets = new HashMap<>();

  private final Lock readLock;
  private final Lock writeLock;

  public WidgetRepository() {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    readLock = readWriteLock.readLock();
    writeLock = readWriteLock.writeLock();
  }

  public Widget save(Widget widget) {
    writeLock.lock();
    try {
      widgets.put(widget.getId(), widget);
      return widget;
    } finally {
      writeLock.unlock();
    }
  }

  public Widget update(Widget widget) {
    writeLock.lock();
    try {
      widgets.replace(widget.getId(), widget);
      return widget;
    } finally {
      writeLock.unlock();
    }
  }

  public void delete(String id) {
    writeLock.lock();
    try {
      widgets.remove(id);
    } finally {
      writeLock.unlock();
    }
  }

  public Widget find(String id) throws WidgetNotFoundException {
    readLock.lock();
    try {
      if (!widgets.containsKey(id)) {
        throw new WidgetNotFoundException(String.format("No widgetId = %s was found", id));
      }
      return widgets.get(id);
    } finally {
      readLock.unlock();
    }
  }

  public List<Widget> findAll() {
    readLock.lock();
    try {
      return widgets.values().stream().sorted(sortByZIndex()).collect(Collectors.toList());
    } finally {
      readLock.unlock();
    }
  }

  private Comparator<Widget> sortByZIndex() {
    return Comparator.comparing(Widget::getZIndex);
  }

  public void clean() {
    widgets.clear();
  }
}
