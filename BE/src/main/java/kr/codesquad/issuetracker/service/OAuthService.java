package kr.codesquad.issuetracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.codesquad.issuetracker.common.exception.UserNotFoundException;
import kr.codesquad.issuetracker.common.security.GithubKey;
import kr.codesquad.issuetracker.common.security.GithubPayload;
import kr.codesquad.issuetracker.common.security.GithubToken;
import kr.codesquad.issuetracker.common.security.GithubUser;
import kr.codesquad.issuetracker.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OAuthService {
    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_API_URL = "https://api.github.com/user";

    @Value("${GITHUB_CLIENT_ID}")
    private String GITHUB_CLIENT_ID;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String GITHUB_CLIENT_SECRET;

    private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

    public GithubToken getTokenFromCode(String code) {
        HttpEntity<GithubPayload> request = new HttpEntity<>(
                GithubPayload.of(new GithubKey(GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET), code));
        return new RestTemplate().postForEntity(GITHUB_ACCESS_TOKEN_URL, request, GithubToken.class)
                .getBody();
    }

    public User getUserInfoToToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + token);
        GithubUser user = Optional.ofNullable(
                new RestTemplate()
                        .exchange(GITHUB_USER_API_URL, HttpMethod.GET, new HttpEntity<>(headers), GithubUser.class)
                        .getBody())
                .orElseThrow(UserNotFoundException::new);

        log.info("user info from github: {}", user);

        if (user.getEmail() == null) {
            user.setEmail(getEmailFromGithub(headers));
        }
        return User.builder()
                .nickname(user.getName())
                .email(user.getEmail())
                .githubToken(token)
                .build();
    }

    private String getEmailFromGithub(HttpHeaders headers) {
        String eamil = new RestTemplate()
                .exchange(GITHUB_USER_API_URL + "/emails", HttpMethod.GET, new HttpEntity<>(headers), String.class)
                .getBody();

        log.info("email info : {}", eamil);
        try {
            JsonNode emailNode = new ObjectMapper().readTree(eamil);
            for (JsonNode jsonNode : emailNode) {
                log.info("JsonNode : {}", jsonNode);
                if (jsonNode.get("primary").asBoolean()) {
                    return jsonNode.get("email").textValue();
                }
            }
        } catch (JsonProcessingException e) {
            log.error("Json Type Error", e);
        }
        return null;
    }
}
