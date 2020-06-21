package kr.codesquad.issuetracker.domain.milestone;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.domain.issue.Issue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Milestone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean isOpened;
  private String title;
  private String description;
  private LocalDate dueDate;

  @OneToMany(mappedBy = "milestone")
  private List<Issue> issues;

  @Builder
  private Milestone(Long id, String title) {
    this.id = id;
    this.title = title;
  }
}