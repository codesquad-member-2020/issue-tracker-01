package kr.codesquad.issuetracker.controller.response;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import kr.codesquad.issuetracker.domain.comment.CommentOfIssue;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.label.LabelOfIssue;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfIssue;
import kr.codesquad.issuetracker.domain.relation.IssueAssignee;
import kr.codesquad.issuetracker.domain.relation.IssueLabel;
import kr.codesquad.issuetracker.domain.user.UserOfIssue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueDetail {

  private Long issueNumber;
  private boolean isOpened;
  private String title;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private UserOfIssue author;
  private List<UserOfIssue> assignees;
  private List<LabelOfIssue> labels;
  private MilestoneOfIssue milestone;
  private List<CommentOfIssue> comments;

  public IssueDetail(Issue issue) {
    this.issueNumber = issue.getIssueNumber();
    this.isOpened = issue.isOpened();
    this.title = issue.getTitle();
    this.createdAt = issue.getCreatedAt();
    this.updatedAt = issue.getUpdatedAt();
    this.author = new UserOfIssue(issue.getAuthor());
    this.assignees = issue.getAssignees().stream()
        .map(IssueAssignee::getAssignee)
        .map(UserOfIssue::new)
        .collect(toList());
    this.labels = issue.getLabels().stream()
        .map(IssueLabel::getLabel)
        .map(LabelOfIssue::new)
        .collect(toList());
    this.milestone = new MilestoneOfIssue(issue.getMilestone());
    this.comments = issue.getComments().stream().map(CommentOfIssue::new).collect(toList());
  }
}
