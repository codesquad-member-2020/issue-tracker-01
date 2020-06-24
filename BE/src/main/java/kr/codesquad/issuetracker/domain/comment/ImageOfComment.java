package kr.codesquad.issuetracker.domain.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageOfComment {

  private String url;

  public ImageOfComment(Image image) {
    this.url = image.getUrl();
  }
}
