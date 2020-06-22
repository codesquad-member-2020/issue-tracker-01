package kr.codesquad.issuetracker.domain.label;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LabelOfIssue {

  private String title;
  private String color;

  public LabelOfIssue(Label label) {
    this.title = label.getTitle();
    this.color = label.getColor();
  }
}
