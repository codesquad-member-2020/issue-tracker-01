package kr.codesquad.issuetracker.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
