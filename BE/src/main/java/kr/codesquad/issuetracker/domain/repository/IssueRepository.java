package kr.codesquad.issuetracker.domain.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kr.codesquad.issuetracker.domain.entity.Issue;
import org.springframework.stereotype.Repository;

@Repository
public class IssueRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Issue issue) {
    em.persist(issue);
    return issue.getIssueNumber();
  }

  public Issue find(Long issueNumber) {
    return em.find(Issue.class, issueNumber);
  }

  public List<Issue> findAll() {
    return em.createQuery("select i from Issue i", Issue.class).getResultList();
  }

  public List<Issue> findOpenedIssues() {
    return em.createQuery("select i from Issue i where i.isOpened = true", Issue.class)
        .getResultList();
  }
}
