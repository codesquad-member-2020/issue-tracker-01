package kr.codesquad.issuetracker.controller.request;

import kr.codesquad.issuetracker.common.exception.BusinessException;
import kr.codesquad.issuetracker.common.exception.ErrorCode;

public enum IssueOpenState {
  OPEN("open"), CLOSE("close");

  private final String state;

  IssueOpenState(String state) {
    this.state = state;
  }

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
