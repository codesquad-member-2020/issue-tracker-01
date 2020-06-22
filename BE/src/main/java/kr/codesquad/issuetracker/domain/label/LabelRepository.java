package kr.codesquad.issuetracker.domain.label;

import java.util.List;
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

  public List<Label> findBySearchParam(String searchParam) {
    return em.createQuery("select l from Label l where l.title like '%" + searchParam + "%'",
        Label.class).getResultList();
  }
}
