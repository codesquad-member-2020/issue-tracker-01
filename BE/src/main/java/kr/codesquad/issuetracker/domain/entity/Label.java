package kr.codesquad.issuetracker.domain.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Label {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String color;
  private String description;

  @OneToMany(mappedBy = "label")
  private List<IssueLabel> issueLabels;

  @Builder
  private Label(Long id, String title, String color, String description) {
    this.id = id;
    this.title = title;
    this.color = color;
    this.description = description;
  }
}
