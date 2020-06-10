package kr.codesquad.issuetracker.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString(of = {"issueNumber", "isOpened", "title", "createdAt", "updatedAt"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue {

    private Long issueNumber; // TODO: JPA 연동시 Auto Increment 적용
    private boolean isOpened;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User author;
    private List<Label> labels;
    private MileStone mileStone;
    private List<User> assignees;

    @Builder
    public Issue(Long issueNumber,
                 boolean isOpened,
                 String title,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt,
                 User author,
                 List<Label> labels,
                 MileStone mileStone,
                 List<User> assignees) {
        this.issueNumber = issueNumber;
        this.isOpened = isOpened;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
        this.labels = labels;
        this.mileStone = mileStone;
        this.assignees = assignees;
    }

    public Issue close() {
        return Issue.builder()
                    .issueNumber(this.issueNumber)
                    .isOpened(false)
                    .title(this.title)
                    .createdAt(this.createdAt)
                    .updatedAt(LocalDateTime.now())
                    .author(this.author)
                    .assignees(this.assignees)
                    .labels(this.labels)
                    .mileStone(this.mileStone)
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
                    .assignees(this.assignees)
                    .labels(this.labels)
                    .mileStone(this.mileStone)
                    .build();
    }
}
