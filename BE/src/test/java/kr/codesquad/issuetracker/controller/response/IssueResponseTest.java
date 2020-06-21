package kr.codesquad.issuetracker.controller.response;

import kr.codesquad.issuetracker.domain.entity.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IssueResponseTest {

    @Test
    @DisplayName("toString method test")
    void toStringMethodTest() {
        List<Issue> issues = new ArrayList<>();
        assertThat(new IssueResponse(issues).toString().contains("@")).isFalse();
    }

    @Test
    @DisplayName("AllArgsConstructor annotation test")
    void allArgsConstructorAnnotationTest() {
        List<Issue> issues = new ArrayList<>();
        assertThat(new IssueResponse(issues).getIssues()).isEqualTo(issues);
    }
}
