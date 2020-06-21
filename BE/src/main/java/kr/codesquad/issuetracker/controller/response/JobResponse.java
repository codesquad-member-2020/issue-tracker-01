package kr.codesquad.issuetracker.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JobResponse {

  private final boolean success;
  private final String message;

  public static JobResponse of(boolean success) {
    return new JobResponse(success, success ? "성공" : "실패");
  }
}
