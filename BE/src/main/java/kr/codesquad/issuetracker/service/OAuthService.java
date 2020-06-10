package kr.codesquad.issuetracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.codesquad.issuetracker.common.security.GithubKey;
import kr.codesquad.issuetracker.common.security.GithubPayload;
import kr.codesquad.issuetracker.common.security.GithubToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {
    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_API_URL = "https://api.github.com/user";


    private static final Logger log = LoggerFactory.getLogger(OAuthService.class);

    private final ObjectMapper objectMapper;
    private final GithubKey githubKey;
    private final RestTemplate restTemplate;

    public OAuthService(ObjectMapper objectMapper, GithubKey githubKey, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.githubKey = githubKey;
        this.restTemplate = restTemplate;
    }

    public GithubToken getTokenFromCode(String code) {
        HttpEntity<GithubPayload> request = new HttpEntity<>(
                GithubPayload.of(githubKey, code));
        return restTemplate.postForEntity(GITHUB_ACCESS_TOKEN_URL, request, GithubToken.class)
                .getBody();
    }

}
