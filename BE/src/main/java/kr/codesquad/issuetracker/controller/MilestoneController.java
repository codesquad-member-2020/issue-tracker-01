package kr.codesquad.issuetracker.controller;

import static kr.codesquad.issuetracker.common.constant.CommonConstant.HOST;

import java.net.URI;
import java.net.URISyntaxException;
import kr.codesquad.issuetracker.controller.request.MilestoneRequest;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.controller.response.MilestoneListResponse;
import kr.codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/milestones")
@RestController
public class MilestoneController {

  private final MilestoneService milestoneService;

  @GetMapping("")
  public MilestoneListResponse showMilestoneList() {
    MilestoneListResponse milestoneListResponse = new MilestoneListResponse(
        milestoneService.findAll());

    log.debug("검색 결과: {}", milestoneListResponse);
    return milestoneListResponse;
  }

  @PostMapping("")
  public ResponseEntity<Void> createMilestone(@RequestBody MilestoneRequest milestoneRequest)
      throws URISyntaxException {
    log.debug("요청 객체: {}", milestoneRequest);

    URI location = new URI(
        HOST + "/milestones/" + milestoneService.createMilestone(milestoneRequest));
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public JobResponse updateMilestone(@PathVariable Long id,
      @RequestBody MilestoneRequest milestoneRequest) {
    log.debug("조회한 Milestone의 id: {}, 요청 객체: {}", id, milestoneRequest);

    return JobResponse.of(milestoneService.updateMilestone(id, milestoneRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
    log.debug("조회한 Milestone의 id: {}", id);

    milestoneService.deleteMilestone(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/open")
  public JobResponse openMilestone(@PathVariable Long id) {
    log.debug("열려는 milestone의 id: {}", id);

    return JobResponse.of(milestoneService.openMilestone(id));
  }

  @PutMapping("/{id}/close")
  public JobResponse closeMilestone(@PathVariable Long id) {
    log.debug("닫으려는 milestone의 id: {}", id);

    return JobResponse.of(milestoneService.closeMilestone(id));
  }
}
