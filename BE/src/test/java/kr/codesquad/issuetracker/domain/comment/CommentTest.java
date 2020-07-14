package kr.codesquad.issuetracker.domain.comment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new Comment().toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor test")
  void noArgsConstructorTest() {
    Comment actual = new Comment();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNull();
    assertThat(actual.getImages()).isNull();
    assertThat(actual.getWriter()).isNull();
    assertThat(actual.getIssue()).isNull();
  }
}
