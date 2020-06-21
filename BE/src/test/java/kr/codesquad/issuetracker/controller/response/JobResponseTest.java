package kr.codesquad.issuetracker.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JobResponseTest {

  public static final String SUCCESS_STRING = "성공";

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new JobResponse(true, SUCCESS_STRING).toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("AllArgsConstructor annotation test")
  void allArgsConstructorAnnotationTest() {
    JobResponse jobResponse = new JobResponse(true, SUCCESS_STRING);
    assertThat(jobResponse.isSuccess()).isTrue();
    assertThat(jobResponse.getMessage()).isEqualTo(SUCCESS_STRING);
  }
}
