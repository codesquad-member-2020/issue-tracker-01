package kr.codesquad.issuetracker.domain.label;

import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.relation.IssueLabel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabelOfLabelList {

  private Long id;
  private String title;
  private String color;
  private String description;
  private Long openedIssueCount; // 최적화 필요. Label마다 쿼리가 날아감.

  public LabelOfLabelList(Label label) {
    this.id = label.getId();
    this.title = label.getTitle();
    this.color = label.getColor();
    this.description = label.getDescription();
    this.openedIssueCount = label.getIssueLabels()
        .stream()
        .map(IssueLabel::getIssue)
        .filter(Issue::isOpened)
        .count();
  }

  @Builder
  public LabelOfLabelList(Long id, String title, String color, String description,
      long openedIssueCount) {
    this.id = id;
    this.title = title;
    this.color = color;
    this.description = description;
    this.openedIssueCount = openedIssueCount;
  }
}
