package kr.codesquad.issuetracker.domain.issue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan({"kr.codesquad.issuetracker.domain"})
class IssueOfIssueListTest {

  @Autowired
  private IssueRepository issueRepository;

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    Issue issue = issueRepository.find(1L);
    assertThat(new IssueOfIssueList(issue).toString().contains("@")).isFalse();
  }
}
