package kr.codesquad.issuetracker.domain;

import lombok.ToString;

import java.util.Map;

@ToString
public class UserDTO {

    private final String nickName;
    private final String eamil;

    private UserDTO(String nickName, String eamil) {
        this.nickName = nickName;
        this.eamil = eamil;
    }

    private UserDTO(Map<String, String> userMap) {
        this.nickName = userMap.get("nickName");
        this.eamil = userMap.get("email");
    }

    public static UserDTO of(String nickName, String eamil) {
        return new UserDTO(nickName, eamil);
    }

    public static UserDTO of(Map<String, String> userMap) {
        return new UserDTO(userMap);
    }
}
