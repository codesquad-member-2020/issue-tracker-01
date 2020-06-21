package kr.codesquad.issuetracker.controller.request;

import kr.codesquad.issuetracker.common.error.ErrorCode;
import kr.codesquad.issuetracker.common.error.exception.BusinessException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IssueOpenState {
  OPEN("open"), CLOSE("close");

  private final String state;

  public static IssueOpenState valueOfState(String state) {
    switch (state) {
      case "open":
        return IssueOpenState.OPEN;
      case "close":
        return IssueOpenState.CLOSE;
      default:
        throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
    }
  }
}
