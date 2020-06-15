package kr.codesquad.issuetracker.common.config;

import kr.codesquad.issuetracker.common.security.GithubKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${GITHUB_CLIENT_ID}")
    private String GITHUB_CLIENT_ID;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String GITHUB_CLIENT_SECRET;

    @Bean
    public GithubKey githubKey() {
        return new GithubKey(GITHUB_CLIENT_ID,GITHUB_CLIENT_SECRET);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
                  .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                  .findFirst()
                  .ifPresent(converter -> ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset(UTF_8));
    }
}
