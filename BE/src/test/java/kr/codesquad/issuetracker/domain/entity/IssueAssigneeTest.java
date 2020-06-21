package kr.codesquad.issuetracker.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssueAssigneeTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new IssueAssignee().toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor test")
  void noArgsConstructorTest() {
    IssueAssignee actual = new IssueAssignee();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNull();
  }
}
