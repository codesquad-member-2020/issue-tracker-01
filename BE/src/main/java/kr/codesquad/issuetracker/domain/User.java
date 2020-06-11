package kr.codesquad.issuetracker.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id; // TODO: JPA 연동시 Auto Increment 적용
    private String userId;
    private String profileImage;

    @Builder
    private User(Long id, String userId, String profileImage) {
        this.id = id;
        this.userId = userId;
        this.profileImage = profileImage;
    }
}
