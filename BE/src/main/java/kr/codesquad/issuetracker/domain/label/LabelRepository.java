package kr.codesquad.issuetracker.domain.label;

import java.util.List;
import java.util.Optional;
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

  public Optional<Label> find(Long id) {
    return Optional.ofNullable(em.find(Label.class, id));
  }

  public void remove(Label label) {
    em.remove(label);
  }

  public List<Label> findByTitle(String title) {
    return em.createQuery("select l from Label l where l.title like '%" + title + "%'",
        Label.class).getResultList();
  }

  public List<Label> findByTitleAndDescription(String keyword) {
    return em.createQuery(
        "select l from Label l where l.title like '%" + keyword + "%'"
            + " or l.description like '%" + keyword + "%'", Label.class).getResultList();
  }
}
