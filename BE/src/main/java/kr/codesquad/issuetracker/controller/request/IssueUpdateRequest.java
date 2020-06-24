package kr.codesquad.issuetracker.controller.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IssueUpdateRequest {

  private String title;
  private List<String> assigneeUserIdList;
  private List<Long> labelIdList;
  private Long milestoneId;
}
