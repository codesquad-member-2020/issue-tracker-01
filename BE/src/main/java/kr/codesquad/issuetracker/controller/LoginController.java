package kr.codesquad.issuetracker.controller;

import io.swagger.annotations.Api;
import kr.codesquad.issuetracker.domain.User;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.LoginService;
import kr.codesquad.issuetracker.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final OAuthService authService;
    private final JwtService jwtService;
    private final LoginService loginService;

    public LoginController(OAuthService authService, JwtService jwtService, LoginService loginService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity<String> loginWithGithub(@CookieValue(value = "jwt", required = false) String jwt) {
        if (jwt != null) {
            log.debug("jwt token : {}", jwt);
            log.debug("jwt token value : {}", jwtService.getUserFromJws(jwt));
            return ResponseEntity.ok(jwtService.getUserFromJws(jwt).toString());
        }
        HttpHeaders headers = loginService.redirectToGithub();
        return new ResponseEntity<>("redirect", headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/oauth")
    public ResponseEntity<String> oauthAuthentication(@RequestParam("code") String code) {
        String accessToken = authService.getTokenFromCode(code).getAccessToken();
        log.debug("AccessToken : {}", accessToken);

        User savedUser = loginService.insertUser(accessToken);
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
