package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.common.security.GithubKey;
import kr.codesquad.issuetracker.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private static final int MAX_AGE = 7 * 24 * 60 * 60;
    public static final URI ROOT_URL = URI.create("http://localhost:8080");
    private static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";

    private final OAuthService authService;
    private final GithubKey githubKey;

    public HttpHeaders redirectToGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(AUTHORIZE_URL)
                .queryParam("client_id", githubKey.getClientId())
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

    public User insertUser(String token) {
        User user = authService.getUserInfoToToken(token);
        log.info("User Info : {}", user);
        return user;
    }

    public HttpHeaders redirectWithCookie(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt);
        headers.add(HttpHeaders.SET_COOKIE, "jwt=" + jwt + "; Path=/" + "; Max-Age=" + MAX_AGE + ";");
        headers.setLocation(ROOT_URL);
        return headers;
    }

}
