package kr.codesquad.issuetracker.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IssueTest {

    @Test
    @DisplayName("이슈 상태 열림 확인 테스트")
    void 이슈_상태_열림_확인_테스트() {
        Issue openedIssue = new Issue();
        assertThat(openedIssue.isOpened()).isTrue();
    }
}
