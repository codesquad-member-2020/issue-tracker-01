package kr.codesquad.issuetracker.controller;

import static kr.codesquad.issuetracker.common.constant.CommonConstant.HOST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kr.codesquad.issuetracker.controller.request.CommentRequest;
import kr.codesquad.issuetracker.controller.request.IssueCreateRequest;
import kr.codesquad.issuetracker.controller.request.IssueUpdateRequest;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.controller.response.IssueDetail;
import kr.codesquad.issuetracker.controller.response.IssueResponse;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterLabelResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterMilestoneResponse;
import kr.codesquad.issuetracker.controller.response.SearchFilterUserResponse;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.LabelService;
import kr.codesquad.issuetracker.service.MilestoneService;
import kr.codesquad.issuetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("")
  public ResponseEntity<Void> createIssue(@RequestBody IssueCreateRequest issueCreateRequest,
      HttpServletRequest request
  ) throws URISyntaxException {
    log.debug("요청 객체: {}", issueCreateRequest);
    Long userId = Long.parseLong(((UserDTO) request.getAttribute("user")).getId());

    URI location = new URI(
        HOST + "/issues/" + issueService.createIssue(issueCreateRequest, userId));

    return ResponseEntity.created(location).build();
  }

  @PutMapping("{issueNumber}")
  public JobResponse updateIssue(@PathVariable Long issueNumber,
      @RequestBody IssueUpdateRequest issueUpdateRequest) {
    log.debug("변경하려는 이슈 번호: {}, 요청 객체: {}", issueNumber, issueUpdateRequest);

    return JobResponse.of(issueService.updateIssue(issueNumber, issueUpdateRequest));
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

  @PostMapping("/{issueNumber}/comments")
  public JobResponse createComment(@PathVariable Long issueNumber,
      @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
    log.debug("Comment를 추가하려는 issueNumber: {}, Comment 추가 정보: {}", issueNumber, commentRequest);
    Long id = Long.parseLong(((UserDTO) request.getAttribute("user")).getId());

    return JobResponse.of(issueService.createComment(issueNumber, commentRequest, id));
  }

  @PutMapping("/{issueNumber}/comments/{commentOrder}")
  public JobResponse updateComment(@PathVariable Long issueNumber,
      @PathVariable int commentOrder,
      @RequestBody CommentRequest commentRequest) {
    log.debug("Comment를 변경하려는 issueNumber: {}, comment id: {}, Comment 추가 정보: {}", issueNumber,
        commentOrder, commentRequest);

    return JobResponse.of(issueService.updateComment(issueNumber, commentOrder, commentRequest));
  }

  @PutMapping("/{issueNumber}/open")
  public JobResponse openIssue(@PathVariable Long issueNumber) {
    log.debug("열려는 issue의 번호: {}", issueNumber);

    return JobResponse.of(issueService.openIssue(issueNumber));
  }

  @PutMapping("/{issueNumber}/close")
  public JobResponse closeIssue(@PathVariable Long issueNumber) {
    log.debug("닫으려는 issue의 번호: {}", issueNumber);

    return JobResponse.of(issueService.closeIssue(issueNumber));
  }
}
