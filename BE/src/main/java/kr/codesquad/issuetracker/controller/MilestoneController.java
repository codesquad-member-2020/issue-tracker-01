package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.controller.request.MilestoneRequest;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.controller.response.MilestoneListResponse;
import kr.codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public JobResponse createMilestone(@RequestBody MilestoneRequest milestoneRequest) {
    log.debug("요청 객체: {}", milestoneRequest);

    return JobResponse.of(milestoneService.createMilestone(milestoneRequest));
  }

  @PutMapping("/{id}")
  public JobResponse updateMilestone(@PathVariable Long id,
      @RequestBody MilestoneRequest milestoneRequest) {
    log.debug("조회한 Milestone의 id: {}, 요청 객체: {}", id, milestoneRequest);

    return JobResponse.of(milestoneService.updateMilestone(id, milestoneRequest));
  }

  @DeleteMapping("/{id}")
  public JobResponse deleteMilestone(@PathVariable Long id) {
    log.debug("조회한 Milestone의 id: {}", id);

    milestoneService.deleteMilestone(id);
    return JobResponse.of(!milestoneService.isExists(id));
  }
}
