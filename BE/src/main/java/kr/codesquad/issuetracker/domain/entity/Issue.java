package kr.codesquad.issuetracker.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private List<IssueLabel> issueLabels;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(mappedBy = "issue")
    private List<IssueAssignee> issueAssignees;

    @Builder
    private Issue(Long issueNumber, boolean isOpened, String title, LocalDateTime createdAt, LocalDateTime updatedAt, User author, Milestone milestone) {
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
