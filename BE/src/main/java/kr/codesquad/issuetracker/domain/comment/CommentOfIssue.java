package kr.codesquad.issuetracker.domain.comment;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
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
public class CommentOfIssue {

  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<ImageOfComment> images;
  private UserOfIssue writer;

  public CommentOfIssue(Comment comment) {
    this.description = comment.getDescription();
    this.createdAt = comment.getCreatedAt();
    this.updatedAt = comment.getUpdatedAt();
    this.images = comment.getImages().stream().map(ImageOfComment::new).collect(toList());
    this.writer = new UserOfIssue(comment.getWriter());
  }
}
