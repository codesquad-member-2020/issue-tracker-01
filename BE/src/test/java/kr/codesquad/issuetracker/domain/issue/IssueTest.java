package kr.codesquad.issuetracker.domain.issue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IssueTest {

  Issue openedIssue;

  @BeforeEach
  void setUp() {
    openedIssue = Issue.builder().isOpened(true).build();
  }

  @Test
  @DisplayName("이슈 상태 열림 확인 테스트")
  void 이슈_상태_열림_확인_테스트() {
    assertThat(openedIssue.isOpened()).isTrue();
  }

  @Test
  @DisplayName("이슈 상태 열림에서 닫힘으로 변경 테스트")
  void 이슈_상태_열림에서_닫힘으로_변경_테스트() {
    assertThat(openedIssue.changeOpenState(false).isOpened()).isFalse();
  }

  @Test
  @DisplayName("이슈 상태 닫힘에서 열림으로 변경 테스트")
  void 이슈_상태_닫힘에서_열림으로_변경_테스트() {
    Issue closedIssue = Issue.builder().isOpened(false).build();
    assertThat(closedIssue.changeOpenState(true).isOpened()).isTrue();
  }

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(openedIssue.toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor test")
  void noArgsConstructorTest() {
    Issue issue = new Issue();
    assertThat(issue.isOpened()).isFalse();
    assertThat(issue.getAuthor()).isNull();
    assertThat(issue.getLabels()).isNull();
    assertThat(issue.getMilestone()).isNull();
    assertThat(issue.getAssignees()).isNull();
    assertThat(issue.getComments()).isNull();
  }
}
