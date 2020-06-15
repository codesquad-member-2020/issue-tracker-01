package kr.codesquad.issuetracker.domain.dto;

import kr.codesquad.issuetracker.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class UserDTO {

    private final String nickname;
    private final String email;

    private UserDTO(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    private UserDTO(Map<String, String> userMap) {
        this.nickname = userMap.get("nickname");
        this.email = userMap.get("email");
    }

    public static UserDTO of(String nickname, String email) {
        return new UserDTO(nickname, email);
    }

    public static UserDTO of(Map<String, String> userMap) {
        return new UserDTO(userMap);
    }

    public static UserDTO of(User user) {
        return new UserDTO(user.getNickname(),user.getEmail());
    }
}
