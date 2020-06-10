package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.common.security.GithubKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final GithubKey githubKey;

    public LoginService(GithubKey githubKey) {
        this.githubKey = githubKey;
    }

    public HttpHeaders redirectToGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString("https://github.com/login/oauth/authorize")
                .queryParam("client_id", githubKey.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

}
