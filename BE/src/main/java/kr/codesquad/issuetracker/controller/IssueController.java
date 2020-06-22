package kr.codesquad.issuetracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.controller.response.IssueResponse;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "이슈에 관련된 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/issues")
@RestController
public class IssueController {

  private final IssueService issueService;

  @ApiOperation(value = "이슈 목록 조회")
  @GetMapping("")
  public IssueResponse showIssues() {
    List<IssueOfIssueList> issues = issueService.findOpenedIssues();
    log.debug("조회된 이슈 목록: {}", issues);

    IssueResponse issueResponse = new IssueResponse(issues);
    log.debug("이슈 목록 반환 객체: {}", issueResponse);

    return issueResponse;
  }

  @PutMapping("/state")
  public JobResponse updateIssuesState(
      @RequestBody IssuesOpenStatusChangeRequest statusChangeRequest) {
    log.debug("요청 객체: {}", statusChangeRequest);

    return JobResponse.of(issueService.updateIssuesOpenStatus(statusChangeRequest));
  }
}
