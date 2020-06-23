package kr.codesquad.issuetracker.domain.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import kr.codesquad.issuetracker.domain.issue.Issue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MilestoneOfMilestoneList {

  private Long id;
  private boolean isOpened;
  private String title;
  private String description;
  private LocalDate dueDate;
  private LocalDateTime lastUpdatedDate;
  private long openIssueCount;
  private long closedIssueCount;
  private Integer complete;

  public MilestoneOfMilestoneList(Milestone milestone) {
    this.id = milestone.getId();
    this.isOpened = milestone.isOpened();
    this.title = milestone.getTitle();
    this.description = milestone.getDescription();
    this.dueDate = milestone.getDueDate();
    List<Issue> issues = milestone.getIssues();
    int issueSize = issues.size();
    setMilestoneLastUpdatedDate(issues, issueSize);
    setIssueCountAndCompletePercentage(issues, issueSize);
  }

  private void setMilestoneLastUpdatedDate(List<Issue> issues, int issueSize) {
    issues.sort(Comparator.comparing(Issue::getUpdatedAt));
    if (issueSize == 0) {
      this.lastUpdatedDate = null;
      return;
    }
    this.lastUpdatedDate = issues.get(0).getUpdatedAt();
  }

  private void setIssueCountAndCompletePercentage(List<Issue> issues, int issueSize) {
    if (issueSize == 0) {
      this.openIssueCount = 0;
      this.closedIssueCount = 0;
      this.complete = null;
      return;
    }
    this.openIssueCount = issues.stream().filter(Issue::isOpened).count();
    this.closedIssueCount = issueSize - this.openIssueCount;
    this.complete = Math.toIntExact(closedIssueCount * 100 / issueSize);
  }
}
