package com.otus.spring.shell;

import com.otus.spring.config.AppProperties;
import com.otus.spring.service.CVSService;
import com.otus.spring.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellComponent {

  private final AppProperties appProperties;
  private final MessageSource messageSource;
  private final TestService testService;
  private final CVSService cvsService;

  private String userName;

  @ShellMethod(value = "Login ", key = { "l", "login" })
  public void loginStudent(String userName) {
    this.userName = userName;
    testService.fillStudentsData(userName);
  }

  @ShellMethod(value = "test ", key = { "t", "test" })
  @ShellMethodAvailability(value = "isUserNameNotNull")
  public String test() {
    var total = "not-passed";
    var correctAnswer = testService.testing();
    if (correctAnswer >= 3) {
      total = "passed";
    }
    cvsService.saveResult(userName, correctAnswer, total);

    return messageSource.getMessage(total, new String[] { userName }, appProperties.getLocale());
  }

  @ShellMethod(value = "To see results", key = { "r", "result" })
  @ShellMethodAvailability(value = "isUserNameNotNull")
  public String results() {
    System.out.println("Show last result of user");
    List<String> data = cvsService.getLastResultByUserName(userName);

    if (data.isEmpty()) {
      return "No user in file with name -" + userName;
    }

    return "Name -" + data.get(0) + " points -" + data.get(1) + " result -" + data.get(2);
  }

  private Availability isUserNameNotNull() {
    return userName == null ? Availability.unavailable("Before LogIn in system") : Availability.available();
  }

}
