package kr.codesquad.issuetracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kr.codesquad.issuetracker.common.exception.LoginRequiredException;
import kr.codesquad.issuetracker.domain.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {

  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final String USER_JWT_KEY = "user";

  public UserDTO getUserFromJws(String jws) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
      return UserDTO.of((LinkedHashMap<String, String>) claims.getBody().get(USER_JWT_KEY));
    } catch (JwtException e) {
      log.error("인증되지 않은 Jwt token jws : {}", jws);
      throw new LoginRequiredException("인증되지 않은 jwt token입니다.");
    }
  }

  public String createUserJws(UserDTO userDTO) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    Map<String, UserDTO> payloads = new HashMap<>();
    payloads.put(USER_JWT_KEY, userDTO);
    return Jwts.builder().setHeader(headers).setClaims(payloads).signWith(key).compact();
  }

  public Map<String, Object> getDataFromJws(String jwtKey, String jws) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
      return (LinkedHashMap) claims.getBody().get(jwtKey);
    } catch (JwtException ex) {
      log.error("인증되지 않은 jwt token입니다. jws: {}", jws);
      throw new LoginRequiredException("인증되지 않은 jwt token입니다.");
    }
  }
}
