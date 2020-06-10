package kr.codesquad.issuetracker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Builder
@Getter
@ToString
public class User {

    @Id
    private Integer id;
    private String email;
    private String nickName;
    private String githubToken;
}
