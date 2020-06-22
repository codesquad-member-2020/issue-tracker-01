package kr.codesquad.issuetracker.controller.request;

public enum IssueOpenState {
  OPEN,
  CLOSE;

  public static IssueOpenState valueOfState(String state) {
    switch (state) {
      case "open":
        return IssueOpenState.OPEN;
      case "close":
        return IssueOpenState.CLOSE;
      default:
        return IssueOpenState.valueOf(state);
    }
  }
}
