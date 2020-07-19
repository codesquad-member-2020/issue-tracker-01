package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import kr.codesquad.issuetracker.common.error.exception.domain.milestone.MilestoneNotFoundException;
import kr.codesquad.issuetracker.controller.request.MilestoneRequest;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfFilter;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfMilestoneList;
import kr.codesquad.issuetracker.domain.milestone.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MilestoneService {

  private final MilestoneRepository milestoneRepository;

  public Long createMilestone(MilestoneRequest milestoneRequest) {
    Milestone milestone = new Milestone(milestoneRequest);
    log.debug("저장 전 milestone: {}", milestone);

    Long milestoneId = milestoneRepository.save(milestone);
    milestone = milestoneRepository.find(milestoneId).orElseThrow(MilestoneNotFoundException::new);
    log.debug("저장 후 milestone: {}", milestone);
    return milestone.getId();
  }

  public boolean updateMilestone(Long id, MilestoneRequest milestoneRequest) {
    Milestone milestone = milestoneRepository.find(id).orElseThrow(MilestoneNotFoundException::new);
    log.debug("조회된 Milestone 정보: {}", milestone);

    milestone.changeInformation(milestoneRequest);
    log.debug("변경된 Milestone 정보: {}", milestone);

    Long milestoneId = milestoneRepository.save(milestone);
    return id.equals(milestoneId);
  }

  public void deleteMilestone(Long id) {
    Milestone milestone = milestoneRepository.find(id).orElseThrow(MilestoneNotFoundException::new);
    log.debug("조회된 Milestone 정보: {}", milestone);

    milestoneRepository.remove(milestone);
    log.debug("삭제 후 Milestone 정보: {}", milestone);
  }

  @Transactional(readOnly = true)
  public boolean isExists(Long id) {
    return milestoneRepository.find(id).isPresent();
  }

  @Transactional(readOnly = true)
  public List<MilestoneOfMilestoneList> findAll() {
    return milestoneRepository.findAll()
        .stream()
        .map(MilestoneOfMilestoneList::new)
        .collect(toList());
  }

  @Transactional(readOnly = true)
  public List<MilestoneOfFilter> findOpenedMilestonesByFilteringKeyword(String keyword) {
    keyword = changeNullToEmptyString(keyword);

    return milestoneRepository.findOpenedMilestoneByTitle(keyword)
        .stream()
        .map(MilestoneOfFilter::new)
        .collect(toList());
  }

  private String changeNullToEmptyString(String keyword) {
    if (keyword == null) {
      keyword = "";
    }
    return keyword;
  }

  public boolean openMilestone(Long id) {
    Milestone milestone = milestoneRepository.find(id).orElseThrow(MilestoneNotFoundException::new);
    milestone.open();
    Long milestoneId = milestoneRepository.save(milestone);
    return id.equals(milestoneId);
  }

  public boolean closeMilestone(Long id) {
    Milestone milestone = milestoneRepository.find(id).orElseThrow(MilestoneNotFoundException::new);
    milestone.close();
    Long milestoneId = milestoneRepository.save(milestone);
    return id.equals(milestoneId);
  }
}
