package kr.codesquad.issuetracker.controller.response;

import java.util.List;
import kr.codesquad.issuetracker.domain.dto.IssueOfIssueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class IssueResponse {

  private final List<IssueOfIssueList> issues;
}
