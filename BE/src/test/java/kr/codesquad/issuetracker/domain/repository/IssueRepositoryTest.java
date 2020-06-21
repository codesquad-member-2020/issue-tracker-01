package kr.codesquad.issuetracker.domain.repository;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import kr.codesquad.issuetracker.domain.entity.Issue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ComponentScan({"kr.codesquad.issuetracker.domain"})
class IssueRepositoryTest {

  @Autowired
  private IssueRepository issueRepository;

  @Test
  @DisplayName("save and find method Test")
  @Transactional
  void saveAndFindMethodTest() {
    Issue issue = Issue.builder().build();
    Long id = issueRepository.save(issue);
    Assertions.assertThat(issueRepository.find(id)).isEqualTo(issue);
  }

  @Test
  @DisplayName("findAll method Test")
  @Transactional
  void findAllMethodTest() {
    Issue issue1 = Issue.builder().build();
    Issue issue2 = Issue.builder().build();
    List<Issue> issues = Arrays.asList(issue1, issue2);
    List<Issue> savedIssues =
        issues.stream().map(issueRepository::save).map(issueRepository::find).collect(toList());
    List<Issue> foundIssues = issueRepository.findAll();
    Assertions.assertThat(foundIssues).isEqualTo(savedIssues);
  }

  @Test
  @DisplayName("findOpenedIssues method Test")
  @Transactional
  void findOpenedIssuesMethodTest() {
    Issue issue1 = Issue.builder().isOpened(true).build();
    Issue issue2 = Issue.builder().isOpened(false).build();
    List<Issue> issues = Arrays.asList(issue1, issue2);
    List<Issue> openedIssues =
        issues.stream()
            .map(issueRepository::save)
            .map(issueRepository::find)
            .filter(Issue::isOpened)
            .collect(toList());
    List<Issue> foundIssues = issueRepository.findOpenedIssues();
    Assertions.assertThat(foundIssues).isEqualTo(openedIssues);
  }
}
