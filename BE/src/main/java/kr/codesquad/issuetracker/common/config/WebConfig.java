package kr.codesquad.issuetracker.common.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.List;
import kr.codesquad.issuetracker.common.security.GithubKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final HandlerInterceptor interceptor;

  @Bean
  public GithubKey githubKey() {
    return new GithubKey(System.getenv("GITHUB_CLIENT_ID"), System.getenv("GITHUB_CLIENT_SECRET"));
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.stream()
        .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
        .findFirst()
        .ifPresent(
            converter ->
                ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset(UTF_8));
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(interceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/favicon.ico",
            "/login/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/docs/api-docs.html");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
        .allowedOrigins("http://localhost:3000");
  }
}
