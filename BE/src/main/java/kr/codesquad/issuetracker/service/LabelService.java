package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import kr.codesquad.issuetracker.common.error.exception.domain.label.LabelNotFoundException;
import kr.codesquad.issuetracker.controller.request.LabelRequest;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.label.LabelOfFilter;
import kr.codesquad.issuetracker.domain.label.LabelOfLabelList;
import kr.codesquad.issuetracker.domain.label.LabelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LabelService {

  private final LabelRepository labelRepository;

  public boolean createLabel(LabelRequest labelRequest) {
    Label label = new Label(labelRequest);
    log.debug("저장 전 label: {}", label);

    Long labelId = labelRepository.save(label);
    label = labelRepository.find(labelId).orElseThrow(LabelNotFoundException::new);
    log.debug("저장 후 label: {}", label);
    return label.getId() != null;
  }

  @Transactional(readOnly = true)
  public List<LabelOfLabelList> findLabelsByQueryString(String queryString) {
    queryString = changeNullToEmptyString(queryString);

    return labelRepository.findBySearchParam(queryString)
        .stream()
        .map(LabelOfLabelList::new)
        .collect(toList());
  }

  @Transactional(readOnly = true)
  public List<LabelOfFilter> findLabelsByFilteringKeyword(String keyword) {
    keyword = changeNullToEmptyString(keyword);

    return labelRepository.findByFilteringKeyword(keyword)
        .stream()
        .map(LabelOfFilter::new)
        .collect(toList());
  }

  public boolean updateLabel(Long id, LabelRequest labelRequest) {
    Label label = labelRepository.find(id).orElseThrow(LabelNotFoundException::new);
    log.debug("조회된 Label 정보: {}", label);

    label.changeInformation(labelRequest);
    log.debug("변경된 Label 정보: {}", label);

    Long labelId = labelRepository.save(label);
    return id.equals(labelId);
  }

  public void deleteLabel(Long id) {
    Label label = labelRepository.find(id).orElseThrow(LabelNotFoundException::new);
    log.debug("조회된 Label 정보: {}", label);

    labelRepository.remove(label);
    log.debug("삭제 후 Label 정보: {}", label);
  }

  @Transactional(readOnly = true)
  public boolean isExists(Long id) {
    return labelRepository.find(id).isPresent();
  }

  private String changeNullToEmptyString(String keyword) {
    if (keyword == null) {
      keyword = "";
    }
    return keyword;
  }
}
