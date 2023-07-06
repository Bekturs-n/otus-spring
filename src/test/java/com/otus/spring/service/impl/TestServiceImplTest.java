package com.otus.spring.service.impl;

import com.otus.spring.config.AppProperties;
import com.otus.spring.model.Task;
import com.otus.spring.service.IOService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

  private static final String PASSED = "user.passed";
  private static final String NOT_PASSED = "user.not.passed";

  @Mock
  private IOService ioService;
  @Mock
  private CVSServiceImpl cvsService;
  @Mock
  private MessageSource messageSource;
  @Mock
  private AppProperties appProperties;

  private TestServiceImpl testService;

  @BeforeEach
  void setUpBeforeClass() {
    testService = new TestServiceImpl(cvsService, messageSource, appProperties, ioService);
  }

  @Test
  void testingNotPassed() {
    List<Task> task = Collections.singletonList(
        Task.builder().id(1).answer("Answer").question("Question").option(Arrays.asList("op1", "op2")).build());

    when(cvsService.getAll()).thenReturn(task);
    when(ioService.getNextString()).thenReturn("Answer");
    doNothing().when(ioService).setNextString(anyString());
    when(messageSource.getMessage(any(), any(), any())).thenReturn("Passed");
    when(messageSource.getMessage(eq(NOT_PASSED), any(), any())).thenAnswer(invocationOnMock -> "Not passed");

    Assertions.assertEquals("Not passed", testService.testing());
  }

  @Test
  void testingPassed() {
    List<Task> task = Arrays.asList(
        Task.builder().id(1).answer("Answer").question("Question").option(Arrays.asList("op1", "op2")).build(),
        Task.builder().id(1).answer("Answer").question("Question").option(Arrays.asList("op1", "op2")).build(),
        Task.builder().id(1).answer("Answer").question("Question").option(Arrays.asList("op1", "op2")).build());

    when(cvsService.getAll()).thenReturn(task);
    when(ioService.getNextString()).thenReturn("Answer");
    doNothing().when(ioService).setNextString(anyString());
    when(messageSource.getMessage(any(), any(), any())).thenReturn("Not passed");
    when(messageSource.getMessage(eq(PASSED), any(), any())).thenAnswer(invocationOnMock -> "Passed");

    Assertions.assertEquals("Passed", testService.testing());
  }
}