package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.common.security.GithubKey;
import kr.codesquad.issuetracker.domain.User;
import kr.codesquad.issuetracker.domain.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private static int MAX_AGE = 7 * 24 * 60 * 60;
    private final OAuthService authService;
    @Value("${GITHUB_CLIENT_ID}")
    private String GITHUB_CLIENT_ID;

    public LoginService(OAuthService authService) {
        this.authService = authService;
    }

    public HttpHeaders redirectToGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString("https://github.com/login/oauth/authorize")
                .queryParam("client_id", GITHUB_CLIENT_ID)
                .queryParam("scope", "user")
                .build()
                .toUri();
        headers.setLocation(uri);
        return headers;
    }

    @Transactional
    public User insertUser(String token) {
        User user = authService.getUserInfoToToken(token);
        log.info("User Info : {}", user);
        return user;
    }

    public UserDTO createUserDTO(User user) {
        return UserDTO.of(user.getNickname(), user.getEmail());
    }

    public HttpHeaders redirectWithCookie(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt);
        headers.add(HttpHeaders.SET_COOKIE, "jwt=" + jwt + "; Path=/" + "; Max-Age=" + MAX_AGE + ";");
        headers.setLocation(URI.create("http://localhost:8080"));
        return headers;
    }

}
