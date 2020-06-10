package kr.codesquad.issuetracker.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileStone {

    private Long id; // TODO: JPA 연동시 Auto Increment 적용
    private String title;

    @Builder
    public MileStone(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
