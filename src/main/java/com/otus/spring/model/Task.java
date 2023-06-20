package com.otus.spring.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Task {

  private Integer id;
  private String question;
  private List<String> option;
  private String answer;

}
