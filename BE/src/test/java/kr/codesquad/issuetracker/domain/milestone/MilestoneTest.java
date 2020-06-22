package kr.codesquad.issuetracker.domain.milestone;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MilestoneTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(Milestone.builder().id(1L).title("Phase1").build().toString().contains("*"))
        .isFalse();
  }

  @Test
  @DisplayName("NoArgsConstructor annotation test")
  void noArgsConstructorAnnotationTest() {
    Milestone milestone = new Milestone();
    assertThat(milestone.getId()).isNull();
    assertThat(milestone.getTitle()).isNull();
  }
}
