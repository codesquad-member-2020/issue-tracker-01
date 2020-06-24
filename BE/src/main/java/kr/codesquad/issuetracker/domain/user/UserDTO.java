package kr.codesquad.issuetracker.domain.user;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDTO {

  private final String id;
  private final String userId;
  private final String nickname;
  private final String email;

  private UserDTO(String id, String userId, String nickname, String email) {
    this.id = id;
    this.userId = userId;
    this.nickname = nickname;
    this.email = email;
  }

  public static UserDTO of(String id, String userId, String nickname, String email) {
    return new UserDTO(id, userId, nickname, email);
  }

  public static UserDTO of(Map<String, String> userMap) {
    return new UserDTO(userMap.get("id"), userMap.get("userId"), userMap.get("nickname"),
        userMap.get("email"));
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.getId().toString(), user.getUserId(), user.getNickname(),
        user.getEmail());
  }
}
