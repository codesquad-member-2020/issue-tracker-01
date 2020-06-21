package kr.codesquad.issuetracker.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new Image().toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor test")
  void noArgsConstructorTest() {
    Comment actual = new Comment();
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNull();
  }
}
