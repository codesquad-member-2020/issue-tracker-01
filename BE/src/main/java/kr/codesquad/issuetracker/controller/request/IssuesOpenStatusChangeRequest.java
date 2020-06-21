package kr.codesquad.issuetracker.controller.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IssuesOpenStatusChangeRequest {

  private List<Long> issueNumbers;
  private IssueOpenState state;

  public void setState(String state) {
    this.state = IssueOpenState.valueOfState(state);
  }
}
