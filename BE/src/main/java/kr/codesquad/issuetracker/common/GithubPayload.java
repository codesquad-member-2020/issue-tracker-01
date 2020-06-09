package kr.codesquad.issuetracker.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GithubPayload {

    @JsonProperty("client_id")
    private final String clientId;

    @JsonProperty("client_secret")
    private final String clientSecret;

    private final String code;

    private GithubPayload(String clientId, String clientSecret, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    public static GithubPayload of(GithubKey githubKey, String code) {
        return new GithubPayload(githubKey.getClientId(), githubKey.getClientSecret(), code);
    }
}
