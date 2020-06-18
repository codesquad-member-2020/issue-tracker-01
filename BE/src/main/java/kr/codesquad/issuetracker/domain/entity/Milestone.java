package kr.codesquad.issuetracker.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
