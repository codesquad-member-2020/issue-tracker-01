package kr.codesquad.issuetracker.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import kr.codesquad.issuetracker.domain.dto.IssueOfIssueList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssueResponseTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    List<IssueOfIssueList> issues = new ArrayList<>();
    assertThat(new IssueResponse(issues).toString().contains("@")).isFalse();
  }

  @Test
  @DisplayName("AllArgsConstructor annotation test")
  void allArgsConstructorAnnotationTest() {
    List<IssueOfIssueList> issues = new ArrayList<>();
    assertThat(new IssueResponse(issues).getIssues()).isEqualTo(issues);
  }
}
