package kr.codesquad.issuetracker.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabelRequest {

  private String title;
  private String color;
  private String description;
}
