package kr.codesquad.issuetracker.controller;

import static kr.codesquad.issuetracker.common.constant.CommonConstant.HOST;

import java.net.URI;
import java.net.URISyntaxException;
import kr.codesquad.issuetracker.controller.request.LabelRequest;
import kr.codesquad.issuetracker.controller.response.JobResponse;
import kr.codesquad.issuetracker.controller.response.LabelListResponse;
import kr.codesquad.issuetracker.service.LabelService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/labels")
@RestController
public class LabelController {

  private final LabelService labelService;

  @GetMapping("")
  public LabelListResponse showLabelList(@RequestParam(required = false) String title) {
    log.debug("검색한 label title: {}", title);
    LabelListResponse labelListResponse = new LabelListResponse(
        labelService.findLabelsByQueryString(title));

    log.debug("검색 결과: {}", labelListResponse);
    return labelListResponse;
  }

  @PostMapping("")
  public ResponseEntity<URI> createLabel(@RequestBody LabelRequest labelRequest)
      throws URISyntaxException {
    log.debug("요청 객체: {}", labelRequest);

    URI location = new URI(HOST + "/labels/" + labelService.createLabel(labelRequest));
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public JobResponse updateLabel(@PathVariable Long id, @RequestBody LabelRequest labelRequest) {
    log.debug("조회한 Label의 id: {}, 요청 객체: {}", id, labelRequest);

    return JobResponse.of(labelService.updateLabel(id, labelRequest));
  }

  @DeleteMapping("/{id}")
  public JobResponse deleteLabel(@PathVariable Long id) {
    log.debug("조회한 Label의 id: {}", id);

    labelService.deleteLabel(id);
    return JobResponse.of(!labelService.isExists(id));
  }
}
