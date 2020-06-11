package kr.codesquad.issuetracker.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("toString method test")
    void toStringMethodTest() {
        assertThat(User.builder()
                       .id(1L)
                       .userId("ksundong")
                       .profileImage("https://i.imgur.com/U6TiNDE.png")
                       .build()
                       .toString()
                       .contains("*")).isFalse();
    }

    @Test
    @DisplayName("NoArgsConstructor annotation test")
    void noArgsConstructorAnnotationTest() {
        User user = new User();
        assertThat(user.getId()).isNull();
        assertThat(user.getUserId()).isNull();
        assertThat(user.getProfileImage()).isNull();
    }
}
