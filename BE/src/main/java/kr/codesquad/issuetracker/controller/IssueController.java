package kr.codesquad.issuetracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import kr.codesquad.issuetracker.controller.request.IssueCreateRequest;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.controller.response.IssueDetail;
import kr.codesquad.issuetracker.controller.response.IssueResponse;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterLabelResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterMilestoneResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterUserResponse;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.LabelService;
import kr.codesquad.issuetracker.service.MilestoneService;
import kr.codesquad.issuetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "이슈에 관련된 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/issues")
@RestController
public class IssueController {

  private final IssueService issueService;
  private final LabelService labelService;
  private final UserService userService;
  private final MilestoneService milestoneService;

  @ApiOperation(value = "이슈 목록 조회")
  @GetMapping("")
  public IssueResponse showIssues() {
    List<IssueOfIssueList> issues = issueService.findOpenedIssues();
    log.debug("조회된 이슈 목록: {}", issues);

    IssueResponse issueResponse = new IssueResponse(issues);
    log.debug("이슈 목록 반환 객체: {}", issueResponse);

    return issueResponse;
  }

  @GetMapping("/{issueNumber}")
  public IssueDetail showIssueDetail(@PathVariable Long issueNumber) {
    log.debug("상세 정보를 보기 원하는 issue의 번호: {}", issueNumber);

    return issueService.findIssueDetail(issueNumber);
  }

  @PostMapping
  public JobResponse createIssue(@RequestBody IssueCreateRequest issueCreateRequest) {
    log.debug("요청 객체: {}", issueCreateRequest);

    return JobResponse.of(issueService.createIssue(issueCreateRequest));
  }

  @PutMapping("/state")
  public JobResponse updateIssuesState(
      @RequestBody IssuesOpenStatusChangeRequest statusChangeRequest) {
    log.debug("요청 객체: {}", statusChangeRequest);

    return JobResponse.of(issueService.updateIssuesOpenStatus(statusChangeRequest));
  }

  @GetMapping("/search-filter/labels")
  public SearchFilterLabelResponse getLabelSearchFilter(
      @RequestParam(required = false) String keyword) {
    log.debug("검색한 keyword: {}", keyword);

    return new SearchFilterLabelResponse(labelService.findLabelsByFilteringKeyword(keyword));
  }

  @GetMapping("/search-filter/milestones")
  public SearchFilterMilestoneResponse getMilestoneSearchFilter(
      @RequestParam(required = false) String keyword) {
    log.debug("검색한 keyword: {}", keyword);

    return new SearchFilterMilestoneResponse(
        milestoneService.findOpenedMilestonesByFilteringKeyword(keyword));
  }

  @GetMapping("/search-filter/users")
  public SearchFilterUserResponse getUserSearchFilter(
      @RequestParam(required = false) String keyword) {
    log.debug("검색한 keyword: {}", keyword);

    return new SearchFilterUserResponse(userService.findUsersByFilteringKeyword(keyword));
  }
}
