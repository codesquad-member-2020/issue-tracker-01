package kr.codesquad.issuetracker.controller;

import io.swagger.annotations.Api;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.LoginService;
import kr.codesquad.issuetracker.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
@RestController
public class LoginController {

  private final OAuthService authService;
  private final JwtService jwtService;
  private final LoginService loginService;

  @GetMapping
  public ResponseEntity<Object> loginWithGithub(
      @CookieValue(value = "jwt", required = false) String jwt,
      HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (jwt != null) {
      log.debug("jwt token : {}", jwt);
      String userAgent = request.getHeader("User-Agent");
      if (userAgent != null && userAgent.matches(".+(iOS|iPad).+")) {
        response.sendRedirect("issue://oauth?token=" + jwt);
        return new ResponseEntity<>(HttpStatus.FOUND);
      }

      log.debug("jwt token value : {}", jwtService.getUserFromJws(jwt));
      HttpHeaders headers = loginService.redirectWithCookie(jwt);

      return new ResponseEntity<>("redirect", headers, HttpStatus.FOUND);
    }
    HttpHeaders headers = loginService.redirectToGithub();
    return new ResponseEntity<>("redirect", headers, HttpStatus.SEE_OTHER);
  }

  @GetMapping("/oauth")
  public ResponseEntity<String> oauthAuthentication(@RequestParam String code,
      HttpServletRequest request, HttpServletResponse response) throws IOException {
    String accessToken = authService.getTokenFromCode(code).getAccessToken();
    log.debug("AccessToken : {}", accessToken);

    UserDTO user = loginService.insertUser(accessToken);
    String jws = jwtService.createUserJws(user);
    log.debug("Jwt String : {}", jws);

    HttpHeaders headers = loginService.redirectWithCookie(jws);

    String userAgent = request.getHeader("User-Agent");
    if (userAgent != null && userAgent.matches(".+(iPhone|iPad).+")) {
      response.sendRedirect("issue://oauth?token=" + jws);
      return new ResponseEntity<>(HttpStatus.FOUND);
    }

    return new ResponseEntity<>("redirect", headers, HttpStatus.FOUND);
  }
}
