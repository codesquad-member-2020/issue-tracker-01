package kr.codesquad.issuetracker.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubKey {
    private final String clientId;
    private final String clientSecret;

    public GithubKey(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
