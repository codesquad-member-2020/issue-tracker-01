package kr.codesquad.issuetracker.domain.user;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDTO {

  private final String nickname;
  private final String email;

  private UserDTO(String nickname, String email) {
    this.nickname = nickname;
    this.email = email;
  }

  public static UserDTO of(String nickname, String email) {
    return new UserDTO(nickname, email);
  }

  public static UserDTO of(Map<String, String> userMap) {
    return new UserDTO(userMap.get("nickname"), userMap.get("email"));
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.getNickname(), user.getEmail());
  }
}
