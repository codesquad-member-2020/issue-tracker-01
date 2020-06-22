package kr.codesquad.issuetracker.domain.label;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class LabelRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Label label) {
    em.persist(label);
    return label.getId();
  }

  public Label find(Long id) {
    return em.find(Label.class, id);
  }
}
