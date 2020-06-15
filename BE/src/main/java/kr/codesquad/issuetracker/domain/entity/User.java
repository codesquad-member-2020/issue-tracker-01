package kr.codesquad.issuetracker.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id; // TODO: JPA 연동시 Auto Increment 적용
    private String userId;
    private String email;
    private String nickname;
    private String githubToken;
    private String profileImage;

    @Builder
    private User(Long id, String userId, String email, String nickname, String githubToken, String profileImage) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.githubToken = githubToken;
        this.profileImage = profileImage;
    }
}
