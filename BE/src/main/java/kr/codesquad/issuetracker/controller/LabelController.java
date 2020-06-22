package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.controller.request.LabelCreateRequest;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.service.LabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/labels")
@RestController
public class LabelController {

  private final LabelService labelService;

  @PostMapping("")
  public JobResponse createLabel(@RequestBody LabelCreateRequest labelCreateRequest) {
    log.debug("요청 객체: {}", labelCreateRequest);

    return JobResponse.of(labelService.createLabel(labelCreateRequest));
  }
}
