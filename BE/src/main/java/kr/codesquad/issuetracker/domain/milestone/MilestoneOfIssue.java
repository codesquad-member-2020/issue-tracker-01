package kr.codesquad.issuetracker.domain.milestone;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MilestoneOfIssue {

  private String title;

  public MilestoneOfIssue(Milestone milestone) {
    this.title = milestone.getTitle();
  }
}
