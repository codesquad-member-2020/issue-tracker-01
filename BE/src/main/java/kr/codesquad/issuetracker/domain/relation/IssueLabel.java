package kr.codesquad.issuetracker.domain.relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class IssueLabel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "label_id")
  private Label label;

  @ManyToOne
  @JoinColumn(name = "issue_id")
  private Issue issue;

  @Builder
  public IssueLabel(Long id, Label label, Issue issue) {
    this.id = id;
    this.label = label;
    this.issue = issue;
  }
}
