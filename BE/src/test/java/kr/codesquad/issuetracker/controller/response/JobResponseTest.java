package kr.codesquad.issuetracker.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JobResponseTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(JobResponse.of(true).toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("AllArgsConstructor annotation test")
  void allArgsConstructorAnnotationTest() {
    JobResponse jobResponse = JobResponse.of(true);
    assertThat(jobResponse.isSuccess()).isTrue();
  }
}
