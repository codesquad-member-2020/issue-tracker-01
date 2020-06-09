package kr.codesquad.issuetracker.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping
    public ResponseEntity<String> loginWithGithub() {
        return new ResponseEntity<>();
    }

    @GetMapping("/oauth")
    public ResponseEntity<String> oauthAuthentication() {
        return new ResponseEntity<>();
    }
}
