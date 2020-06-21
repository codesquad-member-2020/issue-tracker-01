package kr.codesquad.issuetracker.common.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {

  private String login;
  private String name;
  private String email;
  private String token;

  @JsonProperty("avatar_url")
  private String avatarUrl;
}
