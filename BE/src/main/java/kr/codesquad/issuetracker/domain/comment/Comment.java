package kr.codesquad.issuetracker.domain.comment;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@ToString(of = {"id", "description", "createdAt", "updatedAt"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
  private List<Image> images;

  @ManyToOne
  @JoinColumn(name = "writer_id")
  private User writer;

  @ManyToOne
  @JoinColumn(name = "issue_id")
  private Issue issue;
}
