package kr.codesquad.issuetracker.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
