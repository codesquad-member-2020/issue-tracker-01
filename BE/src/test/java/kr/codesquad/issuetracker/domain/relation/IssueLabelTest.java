package kr.codesquad.issuetracker.domain.relation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssueLabelTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new IssueLabel().toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor test")
  void noArgsConstructorTest() {
    IssueLabel actual = new IssueLabel();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNull();
  }
}
