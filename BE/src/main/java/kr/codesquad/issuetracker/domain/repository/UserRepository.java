package kr.codesquad.issuetracker.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import kr.codesquad.issuetracker.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(User user) {
    em.persist(user);
    return user.getId();
  }

  public User find(Long id) {
    return em.find(User.class, id);
  }
}
