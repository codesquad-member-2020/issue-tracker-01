package kr.codesquad.issuetracker.controller.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssuesOpenStatusChangeRequestTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    assertThat(new IssuesOpenStatusChangeRequest().toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("getter and setter test")
  void getterAndSetterTest() {
    IssuesOpenStatusChangeRequest issuesOpenStatusChangeRequest =
        new IssuesOpenStatusChangeRequest();
    List<Long> issueNumbers = Arrays.asList(1L, 2L);
    String open = "open";
    issuesOpenStatusChangeRequest.setIssueNumbers(issueNumbers);
    issuesOpenStatusChangeRequest.setState(open);
    assertThat(issuesOpenStatusChangeRequest.getIssueNumbers()).isEqualTo(issueNumbers);
    assertThat(issuesOpenStatusChangeRequest.getState()).isEqualTo(open);
  }
}
