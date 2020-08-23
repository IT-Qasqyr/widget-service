package com.miro.widget.controller;

import com.miro.widget.model.WidgetData;
import com.miro.widget.service.WidgetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WidgetController.class)
class WidgetControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private WidgetService widgetService;

  @Test
  void testCreateWidget() throws Exception {
    mvc.perform(
            post("/api/widget")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "  \"x\": 1,\n"
                        + "  \"y\": 2,\n"
                        + "  \"zIndex\": 1,\n"
                        + "  \"width\": 5,\n"
                        + "  \"height\": 10\n"
                        + "}"))
        .andExpect(status().isCreated());

    ArgumentCaptor<WidgetData> argCap = ArgumentCaptor.forClass(WidgetData.class);

    verify(widgetService, times(1)).saveWidget(argCap.capture());

    WidgetData data = argCap.getValue();

    assertThat(
        data,
        Matchers.allOf(
            Matchers.hasProperty("x", Matchers.is(1)),
            Matchers.hasProperty("y", Matchers.is(2)),
//            Matchers.hasProperty("zIndex", Matchers.is(1)),
            Matchers.hasProperty("width", Matchers.is(5)),
            Matchers.hasProperty("height", Matchers.is(10)),
            Matchers.hasProperty("date", Matchers.any(Date.class))));
  }

  @Test
  void testEditWidget() throws Exception {
    mvc.perform(
            put("/api/widget/{id}", "widget1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "  \"x\": 1,\n"
                        + "  \"y\": 3,\n"
                        + "  \"zIndex\": 1,\n"
                        + "  \"width\": 5,\n"
                        + "  \"height\": 10\n"
                        + "}"))
        .andExpect(status().isOk());

    ArgumentCaptor<WidgetData> argCap = ArgumentCaptor.forClass(WidgetData.class);

    verify(widgetService, times(1)).editWidget(argCap.capture());

    WidgetData data = argCap.getValue();

    assertThat(
        data,
        Matchers.allOf(
            Matchers.hasProperty("id", Matchers.is("widget1")),
            Matchers.hasProperty("x", Matchers.is(1)),
            Matchers.hasProperty("y", Matchers.is(3)),
//            Matchers.hasProperty("zIndex", Matchers.is(1)),
            Matchers.hasProperty("width", Matchers.is(5)),
            Matchers.hasProperty("height", Matchers.is(10)),
            Matchers.hasProperty("date", Matchers.any(Date.class))));
  }

  @Test
  void testDeleteWidget() throws Exception {
    mvc.perform(
            delete("/api/widget/{id}", "widget1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    ArgumentCaptor<WidgetData> argCap = ArgumentCaptor.forClass(WidgetData.class);

    verify(widgetService, times(1)).deleteWidget(argCap.capture());

    WidgetData data = argCap.getValue();

    assertThat(
        data,
        Matchers.allOf(
            Matchers.hasProperty("id", Matchers.is("widget1")),
            Matchers.hasProperty("date", Matchers.any(Date.class))));
  }

  @Test
  void testGetWidget() throws Exception {
    mvc.perform(
            get("/api/widget/{id}", "widget1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(widgetService, times(1)).getWidget(eq("widget1"));
  }

  @Test
  void testGetAllWidgets() throws Exception {
    mvc.perform(get("/api/widget").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(widgetService, times(1)).getAllWidgets();
  }
}
