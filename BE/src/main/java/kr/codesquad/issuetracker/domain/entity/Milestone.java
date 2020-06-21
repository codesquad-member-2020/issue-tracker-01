package kr.codesquad.issuetracker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Milestone {

    private Long id; // TODO: JPA 연동시 Auto Increment 적용
    private String title;

    @Builder
    private Milestone(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
