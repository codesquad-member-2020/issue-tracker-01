package kr.codesquad.issuetracker.domain.milestone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MilestoneOfIssue {

  private String title;

  public MilestoneOfIssue(Milestone milestone) {
    this.title = milestone.getTitle();
  }
}
