package kr.codesquad.issuetracker.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import javax.servlet.http.Cookie;
import kr.codesquad.issuetracker.common.security.GithubToken;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.LoginService;
import kr.codesquad.issuetracker.service.OAuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

@WebMvcTest(controllers = {LoginController.class})
class LoginControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  OAuthService oAuthService;

  @MockBean
  JwtService jwtService;

  @MockBean
  LoginService loginService;

  @Test
  @DisplayName("JWT 없이 로그인 테스트")
  void JWT_없이_로그인_테스트() throws Exception {
    // given
    HttpHeaders headers = new HttpHeaders();
    String authUrl = "https://github.com/login/oauth/authorize";
    String clientId = "59679fc1100d620bd132";
    URI uri =
        UriComponentsBuilder.fromUriString(authUrl)
            .queryParam("client_id", clientId)
            .queryParam("scope", "user")
            .build()
            .toUri();
    headers.setLocation(uri);
    when(loginService.redirectToGithub()).thenReturn(headers);

    // then
    mockMvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isSeeOther())
        .andExpect(redirectedUrl(authUrl + "?client_id=" + clientId + "&scope=user"));
  }

  @Test
  @DisplayName("JWT 있을 때 로그인 테스트")
  void JWT_있을_떄_로그인_테스트() throws Exception {
    // given
    String jwt = "jwt";
    UserDTO userDTO = UserDTO.of("1", "test", "test", "test@test.com");
    when(jwtService.getUserFromJws(jwt)).thenReturn(userDTO);

    mockMvc.perform(
        get("/login").contentType(MediaType.APPLICATION_JSON).cookie(new Cookie("jwt", jwt)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.nickname", is(userDTO.getNickname())))
        .andExpect(jsonPath("$.email", is(userDTO.getEmail())));
  }

  @Test
  @DisplayName("OAuth Redirect Test")
  void oAuthRedirectTest() throws Exception {
    // given
    String jwt = "jwt";
    String code = "code";
    String accessToken = "token";
    GithubToken githubToken = new GithubToken();
    githubToken.setAccessToken(accessToken);
    UserDTO userDTO = UserDTO.of("1", "test", "test", "test@test.com");

    String redirectUrl = "http://localhost:8080";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(jwt);
    headers.add(HttpHeaders.SET_COOKIE,
        "jwt=" + jwt + "; Path=/" + "; Max-Age=" + 7 * 24 * 60 * 60 + ";");
    headers.setLocation(URI.create(redirectUrl));

    when(oAuthService.getTokenFromCode(code)).thenReturn(githubToken);
    when(loginService.insertUser(accessToken)).thenReturn(userDTO);
    when(jwtService.createUserJws(userDTO)).thenReturn(jwt);
    when(loginService.redirectWithCookie(jwt)).thenReturn(headers);

    mockMvc.perform(
        get("/login/oauth").contentType(MediaType.APPLICATION_JSON).param("code", code))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(redirectedUrl(redirectUrl))
        .andExpect(cookie().value("jwt", jwt))
        .andExpect(cookie().path("jwt", "/"));
  }
}
