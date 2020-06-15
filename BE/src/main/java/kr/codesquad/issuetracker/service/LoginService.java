package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.common.security.GithubKey;
import kr.codesquad.issuetracker.domain.User;
import kr.codesquad.issuetracker.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private static final String ROOT_DIR = "https://github.com/login/oauth/authorize";
    private final OAuthService authService;
    private final GithubKey githubKey;

    public HttpHeaders redirectToGithub() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(ROOT_DIR)
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
