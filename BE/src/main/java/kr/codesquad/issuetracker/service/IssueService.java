package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import kr.codesquad.issuetracker.common.error.exception.domain.label.LabelNotFoundException;
import kr.codesquad.issuetracker.common.error.exception.domain.milestone.MilestoneNotFoundException;
import kr.codesquad.issuetracker.controller.request.IssueCreateRequest;
import kr.codesquad.issuetracker.controller.request.IssueOpenState;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.controller.response.IssueDetail;
import kr.codesquad.issuetracker.domain.comment.Comment;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.issue.IssueRepository;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.label.LabelRepository;
import kr.codesquad.issuetracker.domain.milestone.MilestoneRepository;
import kr.codesquad.issuetracker.domain.relation.IssueAssignee;
import kr.codesquad.issuetracker.domain.relation.IssueLabel;
import kr.codesquad.issuetracker.domain.user.User;
import kr.codesquad.issuetracker.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class IssueService {

  private final IssueRepository issueRepository;
  private final UserRepository userRepository;
  private final LabelRepository labelRepository;
  private final MilestoneRepository milestoneRepository;

  @Transactional(readOnly = true)
  public List<IssueOfIssueList> findOpenedIssues() {
    return issueRepository.findOpenedIssues().stream()
        .map(IssueOfIssueList::new)
        .sorted(((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())))
        .collect(toList());
  }

  public boolean updateIssuesOpenStatus(IssuesOpenStatusChangeRequest statusChangeRequest) {
    boolean state = statusChangeRequest.getState().equals(IssueOpenState.OPEN);

    List<Long> issueNumbers = statusChangeRequest.getIssueNumbers();
    List<Long> updatedIssueNumbers =
        issueNumbers.stream()
            .map(issueRepository::find)
            .map(i -> i.changeOpenState(state))
            .map(issueRepository::save)
            .collect(toList());
    return issueNumbers.equals(updatedIssueNumbers);
  }

  public boolean createIssue(IssueCreateRequest issueCreateRequest) {
    LocalDateTime now = LocalDateTime.now();
    User author = userRepository.find(1L); // TODO: 유저정보 토큰에서 파싱해오기
    List<User> assignees = issueCreateRequest.getAssigneeUserIdList()
        .stream()
        .map(userRepository::findByUserId)
        .collect(toList());
    List<Label> labels = issueCreateRequest.getLabelIdList()
        .stream()
        .map(id -> labelRepository.find(id).orElseThrow(LabelNotFoundException::new))
        .collect(toList());

    Issue newIssue = Issue.builder()
        .isOpened(true)
        .createdAt(now)
        .updatedAt(now)
        .title(issueCreateRequest.getTitle())
        .author(author)
        .milestone(milestoneRepository.find(issueCreateRequest.getMilestoneId()).orElseThrow(
            MilestoneNotFoundException::new))
        .build();
    log.debug("저장 전 issue 정보: {}", newIssue);

    issueRepository.save(newIssue);
    log.debug("저장 후 issue 정보: {}", newIssue);

    assignees.stream()
        .map(assignee -> IssueAssignee.builder().assignee(assignee).issue(newIssue).build())
        .forEach(issueRepository::saveAssignee);
    labels.stream()
        .map(label -> IssueLabel.builder().label(label).issue(newIssue).build())
        .forEach(issueRepository::saveLabel);
    issueRepository.saveComment(Comment.builder()
        .createdAt(now)
        .updatedAt(now)
        .writer(author)
        .description(issueCreateRequest.getComment())
        .issue(newIssue)
        .build());

    return newIssue.getIssueNumber() != null;
  }

  @Transactional(readOnly = true)
  public IssueDetail findIssueDetail(Long issueNumber) {
    return new IssueDetail(issueRepository.find(issueNumber));
  }
}
