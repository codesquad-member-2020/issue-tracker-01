package kr.codesquad.issuetracker.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserOfFilter {

  private String userId;
  private String nickname;
  private String profileImage;

  public UserOfFilter(User user) {
    this.userId = user.getUserId();
    this.nickname = user.getNickname();
    this.profileImage = user.getProfileImage();
  }
}
