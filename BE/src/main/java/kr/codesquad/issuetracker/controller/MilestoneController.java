package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.controller.request.MilestoneRequest;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/milestones")
@RestController
public class MilestoneController {

  private final MilestoneService milestoneService;

  @PostMapping("")
  public JobResponse createMilestone(@RequestBody MilestoneRequest milestoneRequest) {
    log.debug("요청 객체: {}", milestoneRequest);

    return JobResponse.of(milestoneService.createMilestone(milestoneRequest));
  }
}
