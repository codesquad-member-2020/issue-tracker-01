package kr.codesquad.issuetracker.domain.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import kr.codesquad.issuetracker.domain.issue.Issue;
import lombok.Builder;
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
  private Long openIssueCount;
  private Long closedIssueCount;
  private Integer completeRatio;

  @Builder
  private MilestoneOfMilestoneList(Long id, boolean isOpened, String title,
      String description, LocalDate dueDate, LocalDateTime lastUpdatedDate, long openIssueCount,
      long closedIssueCount, Integer completeRatio) {
    this.id = id;
    this.isOpened = isOpened;
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.lastUpdatedDate = lastUpdatedDate;
    this.openIssueCount = openIssueCount;
    this.closedIssueCount = closedIssueCount;
    this.completeRatio = completeRatio;
  }

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
      this.openIssueCount = 0L;
      this.closedIssueCount = 0L;
      this.completeRatio = null;
      return;
    }
    this.openIssueCount = issues.stream().filter(Issue::isOpened).count();
    this.closedIssueCount = issueSize - this.openIssueCount;
    this.completeRatio = Math.toIntExact(closedIssueCount * 100 / issueSize);
  }
}
