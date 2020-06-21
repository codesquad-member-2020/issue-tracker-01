package kr.codesquad.issuetracker.domain.dto;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import kr.codesquad.issuetracker.domain.entity.Issue;
import kr.codesquad.issuetracker.domain.entity.IssueAssignee;
import kr.codesquad.issuetracker.domain.entity.IssueLabel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class IssueOfIssueList {

  private Long issueNumber;
  private boolean isOpened;
  private String title;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private UserOfIssue author;
  private List<UserOfIssue> assignees;
  private List<LabelOfIssue> labels;
  private MilestoneOfIssue milestone;

  public IssueOfIssueList(Issue issue) {
    this.issueNumber = issue.getIssueNumber();
    this.isOpened = issue.isOpened();
    this.title = issue.getTitle();
    this.createdAt = issue.getCreatedAt();
    this.updatedAt = issue.getUpdatedAt();
    this.author = new UserOfIssue(issue.getAuthor());
    this.assignees =
        issue.getAssignees().stream()
            .map(IssueAssignee::getAssignee)
            .map(UserOfIssue::new)
            .collect(toList());
    this.labels =
        issue.getLabels().stream()
            .map(IssueLabel::getLabel)
            .map(LabelOfIssue::new)
            .collect(toList());
    this.milestone = new MilestoneOfIssue(issue.getMilestone());
  }
}
