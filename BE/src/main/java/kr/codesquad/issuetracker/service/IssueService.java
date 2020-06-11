package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Issue;
import kr.codesquad.issuetracker.domain.Label;
import kr.codesquad.issuetracker.domain.Milestone;
import kr.codesquad.issuetracker.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class IssueService {

    public List<Issue> findIssues() {
        // user
        User user1 = User.builder().id(1L).userId("dion").profileImage("image1").build();
        User user2 = User.builder().id(2L).userId("alex").profileImage("image2").build();

        // label
        Label label1 = Label.builder().id(1L).color("#FFFFFF").title("BE").build();
        Label label2 = Label.builder().id(2L).color("#000000").title("FE").build();

        // milestone
        Milestone milestone1 = Milestone.builder().id(1L).title("Phase1").build();
        Milestone milestone2 = Milestone.builder().id(1L).title("Phase1").build();

        // issue
        Issue issue1 = Issue.builder()
                            .issueNumber(1L)
                            .isOpened(true)
                            .title("Test Issue")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .author(user1)
                            .assignees(Arrays.asList(user1, user2))
                            .labels(Collections.singletonList(label1))
                            .mileStone(milestone1)
                            .build();
        Issue issue2 = Issue.builder()
                            .issueNumber(2L)
                            .isOpened(false)
                            .title("Test Issue2")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .author(user1)
                            .assignees(Collections.singletonList(user1))
                            .labels(Collections.singletonList(label1))
                            .mileStone(milestone1)
                            .build();
        Issue issue3 = Issue.builder()
                            .issueNumber(3L)
                            .isOpened(true)
                            .title("Test Issue3")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .author(user2)
                            .assignees(Arrays.asList(user1, user2))
                            .labels(Arrays.asList(label1, label2))
                            .mileStone(milestone2)
                            .build();

        List<Issue> issues = Stream.of(issue1, issue2, issue3).filter(Issue::isOpened).collect(Collectors.toList());
        return issues;
    }
}
