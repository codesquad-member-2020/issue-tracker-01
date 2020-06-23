package kr.codesquad.issuetracker.domain.milestone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MilestoneOfFilter {

  private Long id;
  private String title;

  public MilestoneOfFilter(Milestone milestone) {
    this.id = milestone.getId();
    this.title = milestone.getTitle();
  }
}
