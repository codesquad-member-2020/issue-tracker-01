package kr.codesquad.issuetracker.controller.response;

import kr.codesquad.issuetracker.domain.entity.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class IssueResponse {

    private final List<Issue> issues;
}
