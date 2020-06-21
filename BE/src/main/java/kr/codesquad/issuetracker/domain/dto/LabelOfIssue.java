package kr.codesquad.issuetracker.domain.dto;

import kr.codesquad.issuetracker.domain.entity.Label;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LabelOfIssue {

  private String title;
  private String color;

  public LabelOfIssue(Label label) {
    this.title = label.getTitle();
    this.color = label.getColor();
  }
}
