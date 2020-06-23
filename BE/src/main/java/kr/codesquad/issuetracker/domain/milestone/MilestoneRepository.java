package kr.codesquad.issuetracker.domain.milestone;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MilestoneRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Milestone milestone) {
    em.persist(milestone);
    return milestone.getId();
  }

  public Optional<Milestone> find(Long milestoneId) {
    return Optional.ofNullable(em.find(Milestone.class, milestoneId));
  }

  public void remove(Milestone milestone) {
    em.remove(milestone);
  }
}
