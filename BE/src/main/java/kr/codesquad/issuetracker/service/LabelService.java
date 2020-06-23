package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
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
@Service
public class LabelService {

  private final LabelRepository labelRepository;

  @Transactional
  public boolean createLabel(LabelRequest labelRequest) {
    Label label = new Label(labelRequest);
    log.debug("저장 전 label: {}", label);

    Long labelId = labelRepository.save(label);
    label = labelRepository.find(labelId);
    log.debug("저장 후 label: {}", label);
    return label.getId() != null;
  }

  public List<LabelOfLabelList> findLabelsByQueryString(String queryString) {
    queryString = changeNullToEmptyString(queryString);

    return labelRepository.findBySearchParam(queryString)
        .stream()
        .map(LabelOfLabelList::new)
        .collect(toList());
  }

  public List<LabelOfFilter> findLabelsByFilteringKeyword(String keyword) {
    keyword = changeNullToEmptyString(keyword);

    return labelRepository.findByFilteringKeyword(keyword)
        .stream()
        .map(LabelOfFilter::new)
        .collect(toList());
  }

  private String changeNullToEmptyString(String keyword) {
    if (keyword == null) {
      keyword = "";
    }
    return keyword;
  }
}
