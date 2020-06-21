package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import kr.codesquad.issuetracker.controller.request.IssueOpenState;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.domain.dto.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

  private final IssueRepository issueRepository;

  public List<IssueOfIssueList> findOpenedIssues() {
    return issueRepository.findOpenedIssues().stream().map(IssueOfIssueList::new).collect(toList());
  }

  @Transactional
  public boolean updateIssuesOpenStatus(IssuesOpenStatusChangeRequest statusChangeRequest) {
    boolean state = statusChangeRequest.getState().equals(IssueOpenState.OPEN);

    List<Long> issueNumbers = statusChangeRequest.getIssueNumbers();
    List<Long> updatedIssueNumbers = issueNumbers.stream()
        .map(issueRepository::find)
        .map(i -> i.changeOpenState(state))
        .map(issueRepository::save)
        .collect(toList());
    return issueNumbers.equals(updatedIssueNumbers);
  }
}
