package kr.codesquad.issuetracker.domain.label;

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
