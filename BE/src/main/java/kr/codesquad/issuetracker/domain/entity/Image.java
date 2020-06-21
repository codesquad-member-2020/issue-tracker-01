package kr.codesquad.issuetracker.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {

  @Id
  private Long id;

  private String url;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  private Comment comment;
}
