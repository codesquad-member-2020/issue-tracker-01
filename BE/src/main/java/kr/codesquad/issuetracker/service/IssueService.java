package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class IssueService {

    public List<Issue> findOpenedIssues() {
        return new ArrayList<>();
    }
}
