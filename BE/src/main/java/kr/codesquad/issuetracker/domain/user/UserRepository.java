package kr.codesquad.issuetracker.domain.user;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

  public List<User> findByFilteringKeyword(String keyword) {
    return em.createQuery(
        "select u from User u where u.userId like '%" + keyword + "%'"
            + " or u.nickname like '%" + keyword + "%'", User.class).getResultList();
  }
}
