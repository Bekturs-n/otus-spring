package com.otus.spring.service.impl;

import com.otus.spring.service.IOService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IOServiceImpl implements IOService {

  @Override
  public String getNextString() {
    Scanner scanner = new Scanner(System.in);
    String result = scanner.nextLine();
    scanner.close();
    return result;
  }

  @Override
  public void setNextString(String string) {
    System.out.println(string);
  }

  @Override
  public void makeIndent(){
    System.out.println();
  }

}
