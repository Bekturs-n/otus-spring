package com.otus.spring.shell;

import com.otus.spring.config.AppProperties;
import com.otus.spring.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellComponent {

  private final AppProperties appProperties;
  private final MessageSource messageSource;
  private final TestService testService;

  private String userName;

  @ShellMethod(value = "Login ", key = { "l", "login" })
  public void loginStudent(String userName) {
    this.userName = userName;
    String welcomeMessage = messageSource.getMessage("user.welcome", null, appProperties.getLocale());
    System.out.println(testService.testing());
    System.out.println(welcomeMessage + userName);
  }

}
