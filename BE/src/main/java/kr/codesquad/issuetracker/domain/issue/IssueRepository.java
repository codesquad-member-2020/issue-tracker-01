package kr.codesquad.issuetracker.domain.issue;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kr.codesquad.issuetracker.domain.comment.Comment;
import kr.codesquad.issuetracker.domain.relation.IssueAssignee;
import kr.codesquad.issuetracker.domain.relation.IssueLabel;
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

  public List<Issue> findOpenedIssues() {
    return em.createQuery("select i from Issue i where i.isOpened = true", Issue.class)
        .getResultList();
  }

  public Long saveComment(Comment comment) {
    em.persist(comment);
    return comment.getId();
  }

  public Long saveAssignee(IssueAssignee issueAssignee) {
    em.persist(issueAssignee);
    return issueAssignee.getId();
  }

  public Long saveLabel(IssueLabel issueLabel) {
    em.persist(issueLabel);
    return issueLabel.getId();
  }
}
