package com.miro.widget.controller;

import com.miro.widget.controller.request.WidgetRequest;
import com.miro.widget.service.WidgetServiceImpl;
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

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WidgetController.class)
class WidgetControllerTest {

  private static final String WIDGET_URL_TEMPLATE = "/api/widget";
  private static final String WIDGET_URL_TEMPLATE_WITH_ID = "/api/widget/{id}";

  private final UUID testId = UUID.randomUUID();

  @Autowired private MockMvc mvc;

  @MockBean private WidgetServiceImpl widgetService;

  @Test
  void testCreateWidget() throws Exception {
    mvc.perform(
            post(WIDGET_URL_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "  \"x\": 1,\n"
                        + "  \"y\": 2,\n"
                        + "  \"z\": 1,\n"
                        + "  \"width\": 5,\n"
                        + "  \"height\": 10\n"
                        + "}"))
        .andExpect(status().isCreated());

    ArgumentCaptor<WidgetRequest> argCap =
        ArgumentCaptor.forClass(WidgetRequest.class);

    verify(widgetService, times(1)).saveWidget(argCap.capture());

    WidgetRequest request = argCap.getValue();

    assertThat(
        request,
        Matchers.allOf(
            Matchers.hasProperty("x", Matchers.is(1)),
            Matchers.hasProperty("y", Matchers.is(2)),
            Matchers.hasProperty("z", Matchers.is(1)),
            Matchers.hasProperty("width", Matchers.is(5)),
            Matchers.hasProperty("height", Matchers.is(10))));
  }

  @Test
  void testEditWidget() throws Exception {
    mvc.perform(
            put(WIDGET_URL_TEMPLATE_WITH_ID, testId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "  \"x\": 1,\n"
                        + "  \"y\": 3,\n"
                        + "  \"z\": 1,\n"
                        + "  \"width\": 5,\n"
                        + "  \"height\": 10\n"
                        + "}"))
        .andExpect(status().isOk());

    ArgumentCaptor<WidgetRequest> argCap =
        ArgumentCaptor.forClass(WidgetRequest.class);

    verify(widgetService, times(1)).editWidget(eq(testId), argCap.capture());

    WidgetRequest request = argCap.getValue();

    assertThat(
        request,
        Matchers.allOf(
            Matchers.hasProperty("x", Matchers.is(1)),
            Matchers.hasProperty("y", Matchers.is(3)),
            Matchers.hasProperty("z", Matchers.is(1)),
            Matchers.hasProperty("width", Matchers.is(5)),
            Matchers.hasProperty("height", Matchers.is(10))));
  }

  @Test
  void testDeleteWidget() throws Exception {
    mvc.perform(delete(WIDGET_URL_TEMPLATE_WITH_ID, testId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(widgetService, times(1)).deleteWidget(testId);
  }

  @Test
  void testGetWidget() throws Exception {
    mvc.perform(get(WIDGET_URL_TEMPLATE_WITH_ID, testId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(widgetService, times(1)).getWidget(eq(testId));
  }

  @Test
  void testGetAllWidgets() throws Exception {
    mvc.perform(get(WIDGET_URL_TEMPLATE).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(widgetService, times(1)).getAllWidgets();
  }
}
