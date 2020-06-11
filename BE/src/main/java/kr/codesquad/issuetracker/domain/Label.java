package kr.codesquad.issuetracker.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Label {

    private Long id; // TODO: JPA 연동시 Auto Increment 적용
    private String title;
    private String color;

    @Builder
    private Label(Long id, String title, String color) {
        this.id = id;
        this.title = title;
        this.color = color;
    }
}
