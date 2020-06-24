package kr.codesquad.issuetracker.domain.user;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.domain.comment.Comment;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.relation.IssueAssignee;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;
  private String email;
  private String nickname;
  private String password;
  private String githubToken;
  private String profileImage;

  @OneToMany(mappedBy = "author")
  private List<Issue> issues;

  @OneToMany(mappedBy = "writer")
  private List<Comment> comments;

  @OneToMany(mappedBy = "assignee")
  private List<IssueAssignee> assignedIssues;

  @Builder
  private User(
      Long id,
      String userId,
      String email,
      String nickname,
      String githubToken,
      String profileImage) {
    this.id = id;
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.githubToken = githubToken;
    this.profileImage = profileImage;
  }

  public void changeGithubToken(String githubToken) {
    this.githubToken = githubToken;
  }
}
