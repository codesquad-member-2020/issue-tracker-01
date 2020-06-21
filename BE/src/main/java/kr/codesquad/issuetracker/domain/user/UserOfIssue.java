package kr.codesquad.issuetracker.domain.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserOfIssue {

  private String nickname;
  private String profileImage;

  public UserOfIssue(User user) {
    this.nickname = user.getNickname();
    this.profileImage = user.getProfileImage();
  }
}
