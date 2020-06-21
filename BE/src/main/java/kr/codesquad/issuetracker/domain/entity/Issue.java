package kr.codesquad.issuetracker.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(of = {"issueNumber", "isOpened", "title", "createdAt", "updatedAt"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long issueNumber;

  private boolean isOpened;

  private String title;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @OneToMany(mappedBy = "issue")
  private List<IssueLabel> labels;

  @ManyToOne
  @JoinColumn(name = "milestone_id")
  private Milestone milestone;

  @OneToMany(mappedBy = "issue")
  private List<IssueAssignee> assignees;

  @OneToMany(mappedBy = "issue")
  private List<Comment> comments;

  @Builder
  private Issue(
      Long issueNumber,
      boolean isOpened,
      String title,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      User author,
      Milestone milestone) {
    this.issueNumber = issueNumber;
    this.isOpened = isOpened;
    this.title = title;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.author = author;
    this.milestone = milestone;
  }

  public Issue close() {
    return Issue.builder()
        .issueNumber(this.issueNumber)
        .isOpened(false)
        .title(this.title)
        .createdAt(this.createdAt)
        .updatedAt(LocalDateTime.now())
        .author(this.author)
        .milestone(this.milestone)
        .build();
  }

  public Issue open() {
    return Issue.builder()
        .issueNumber(this.issueNumber)
        .isOpened(true)
        .title(this.title)
        .createdAt(this.createdAt)
        .updatedAt(LocalDateTime.now())
        .author(this.author)
        .milestone(this.milestone)
        .build();
  }
}
