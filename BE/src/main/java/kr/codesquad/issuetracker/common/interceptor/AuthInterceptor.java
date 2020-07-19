package kr.codesquad.issuetracker.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

  private final JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    UserDTO testUser = UserDTO.of("999", "test", "테스트용", "test@idion.dev");
    request.setAttribute("user", testUser);
    return true;
//    if (request.getMethod().equals("OPTIONS")) {
//      log.info("options 메서드는 통과");
//      return true;
//    }
//
//    String requestURI = request.getRequestURI();
//    if (requestURI.equals("/") || requestURI.equals("/error") || requestURI.equals("/csrf")) {
//      log.debug("Swagger에서 사용하는 Request Pattern 통과");
//      return true;
//    }
//
//    Optional<Cookie> jwtCookie = Optional.ofNullable(WebUtils.getCookie(request, "jwt"));
//    try {
//      UserDTO user = jwtService
//          .getUserFromJws(jwtCookie.orElseThrow(LoginRequiredException::new).getValue());
//      request.setAttribute("user", user);
//      log.debug("user: {}", user);
//      log.debug("cookie: {}", jwtCookie);
//    } catch (LoginRequiredException e) {
//      Cookie[] cookies = request.getCookies();
//      for (Cookie cookie : cookies) {
//        if (cookie.getName().equals("jwt")) {
//          log.debug("토큰을 초기화합니다.", e);
//          cookie.setMaxAge(0);
//          response.addCookie(cookie);
//        }
//      }
//
//      response.setStatus(401);
//      return false;
//    }
//    return true;
  }
}
