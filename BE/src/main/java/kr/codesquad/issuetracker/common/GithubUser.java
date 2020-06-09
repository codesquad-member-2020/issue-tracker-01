package kr.codesquad.issuetracker.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {

    private String login;
    private String name;
    private String email;
    private String token;
}
