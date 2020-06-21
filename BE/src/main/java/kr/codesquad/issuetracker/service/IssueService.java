package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.dto.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueOfIssueList> findOpenedIssues() {
        return issueRepository.findOpenedIssues().stream().map(IssueOfIssueList::new).collect(toList());
    }
}
