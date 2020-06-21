package kr.codesquad.issuetracker.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOfIssue {

  private String nickname;
  private String profileImage;

  public UserOfIssue(User user) {
    this.nickname = user.getNickname();
    this.profileImage = user.getProfileImage();
  }
}
