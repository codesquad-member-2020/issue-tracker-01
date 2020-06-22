package kr.codesquad.issuetracker.domain.label;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabelOfFilter {

  private Long id;
  private String title;
  private String color;
  private String description;

  public LabelOfFilter(Label label) {
    this.id = label.getId();
    this.title = label.getTitle();
    this.color = label.getColor();
    this.description = label.getDescription();
  }
}
