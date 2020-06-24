package kr.codesquad.issuetracker.common.interceptor;

import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.common.error.exception.LoginRequiredException;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

  private final JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    if (request.getMethod().equals("OPTIONS")) {
      log.info("options 메서드는 통과");
      return true;
    }

    Optional<Cookie> jwtCookie = Optional.ofNullable(WebUtils.getCookie(request, "jwt"));
    UserDTO user = jwtService
        .getUserFromJws(jwtCookie.orElseThrow(LoginRequiredException::new).getValue());
    request.setAttribute("user", user);
    log.debug("user: {}", user);
    log.debug("cookie: {}", jwtCookie);
    return true;
  }
}
