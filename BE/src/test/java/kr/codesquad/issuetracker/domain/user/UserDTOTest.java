package kr.codesquad.issuetracker.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDTOTest {

  @Test
  @DisplayName("toString method test")
  void toStringMethodTest() {
    String str = UserDTO.of("test", "test@email.com").toString();
    assertThat(str).isEqualTo("UserDTO(nickname=test, email=test@email.com)");
  }

  @Test
  @DisplayName("of method test")
  void ofMethodTest() {
    // given
    String nickname = "test";
    String email = "test@email.com";

    Map<String, String> userMap = new HashMap<>();
    userMap.put("nickname", nickname);
    userMap.put("email", email);

    User user = User.builder().nickname(nickname).email(email).build();

    // then
    UserDTO actual1 = UserDTO.of(nickname, email);
    UserDTO actual2 = UserDTO.of(userMap);
    UserDTO actual3 = UserDTO.of(user);

    assertThat(actual1.getNickname()).isEqualTo(nickname);
    assertThat(actual1.getEmail()).isEqualTo(email);
    assertThat(actual2.getNickname()).isEqualTo(nickname);
    assertThat(actual2.getEmail()).isEqualTo(email);
    assertThat(actual3.getNickname()).isEqualTo(nickname);
    assertThat(actual3.getEmail()).isEqualTo(email);
  }
}
