package kr.codesquad.issuetracker.domain;

import lombok.*;

@Getter
@ToString(of = {"isOpened"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue {

    private boolean isOpened;

    @Builder
    public Issue(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public Issue close() {
        return Issue.builder().isOpened(false).build();
    }

    public Issue open() {
        return Issue.builder().isOpened(true).build();
    }
}
