package kr.codesquad.issuetracker.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private String password;
    private String githubToken;
    private String profileImage;

    @OneToMany(mappedBy = "author")
    private List<Issue> issues;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments;

    @OneToMany(mappedBy = "assignee")
    private List<IssueAssignee> assignedIssues;

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
