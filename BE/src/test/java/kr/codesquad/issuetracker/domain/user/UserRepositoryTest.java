package kr.codesquad.issuetracker.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ComponentScan({"kr.codesquad.issuetracker.domain"})
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("save and find method Test")
  @Transactional
  void saveAndFindMethodTest() {
    User user = User.builder().build();
    Long id = userRepository.save(user);
    Assertions.assertThat(userRepository.find(id)).isEqualTo(user);
  }
}
