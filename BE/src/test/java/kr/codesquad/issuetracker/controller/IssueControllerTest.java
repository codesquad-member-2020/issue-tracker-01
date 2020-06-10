package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.domain.Issue;
import kr.codesquad.issuetracker.domain.Label;
import kr.codesquad.issuetracker.domain.MileStone;
import kr.codesquad.issuetracker.domain.User;
import kr.codesquad.issuetracker.service.IssueService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {IssueController.class})
public class IssueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IssueService issueService;

    @Test
    @DisplayName("오픈된 이슈 조회 검색 테스트")
    void 오픈된_이슈_조회_검색_테스트() throws Exception {
        // given
        // user
        User user1 = User.builder().id(1L).userId("dion").profileImage("image1").build();
        User user2 = User.builder().id(2L).userId("alex").profileImage("image2").build();

        // label
        Label label1 = Label.builder().id(1L).color("#FFFFFF").title("BE").build();
        Label label2 = Label.builder().id(2L).color("#000000").title("FE").build();

        // milestone
        MileStone mileStone1 = MileStone.builder().id(1L).title("Phase1").build();
        MileStone mileStone2 = MileStone.builder().id(1L).title("Phase1").build();

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
                            .mileStone(mileStone1)
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
                            .mileStone(mileStone1)
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
                            .mileStone(mileStone2)
                            .build();

        List<Issue> issues = Stream.of(issue1, issue2, issue3).filter(Issue::isOpened).collect(Collectors.toList());
        when(issueService.findIssues()).thenReturn(issues);

        // then
        mockMvc.perform(get("/issues").contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.issues", hasSize(issues.size())))
               .andExpect(jsonPath("$.issues[0].issueNumber").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       0).getIssueNumber()), Matchers.equalTo((Number) issues.get(0).getIssueNumber().intValue()))))
               .andExpect(jsonPath("$.issues[0].opened", is(issues.get(0).isOpened())))
               .andExpect(jsonPath("$.issues[0].title", is(issues.get(0).getTitle())))
               .andExpect(jsonPath("$.issues[0].createdAt", is(issues.get(0).getCreatedAt().toString())))
               .andExpect(jsonPath("$.issues[0].updatedAt", is(issues.get(0).getUpdatedAt().toString())))
               .andExpect(jsonPath("$.issues[0].author.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0)
                                                                                                                 .getAuthor()
                                                                                                                 .getId()),
                       Matchers.equalTo((Number) issues.get(0).getAuthor().getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].author.userId", is(issues.get(0).getAuthor().getUserId())))
               .andExpect(jsonPath("$.issues[0].author.profileImage", is(issues.get(0).getAuthor().getProfileImage())))
               .andExpect(jsonPath("$.issues[0].assignees", hasSize(issues.get(0).getAssignees().size())))
               .andExpect(jsonPath("$.issues[0].assignees[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       0).getAssignees().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(0).getAssignees().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].assignees[0].userId",
                       is(issues.get(0).getAssignees().get(0).getUserId())))
               .andExpect(jsonPath("$.issues[0].assignees[0].profileImage",
                       is(issues.get(0).getAssignees().get(0).getProfileImage())))
               .andExpect(jsonPath("$.issues[0].assignees[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       0).getAssignees().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(0).getAssignees().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].assignees[1].userId",
                       is(issues.get(0).getAssignees().get(1).getUserId())))
               .andExpect(jsonPath("$.issues[0].assignees[1].profileImage",
                       is(issues.get(0).getAssignees().get(1).getProfileImage())))
               .andExpect(jsonPath("$.issues[0].labels", hasSize(issues.get(0).getLabels().size())))
               .andExpect(jsonPath("$.issues[0].labels[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       0).getLabels().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(0).getLabels().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].labels[0].title", is(issues.get(0).getLabels().get(0).getTitle())))
               .andExpect(jsonPath("$.issues[0].labels[0].color", is(issues.get(0).getLabels().get(0).getColor())))
               .andExpect(jsonPath("$.issues[0].mileStone.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       0).getMileStone().getId()),
                       Matchers.equalTo((Number) issues.get(0).getMileStone().getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].mileStone.title", is(issues.get(0).getMileStone().getTitle())))
               // issue3
               .andExpect(jsonPath("$.issues[1].issueNumber").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getIssueNumber()), Matchers.equalTo((Number) issues.get(1).getIssueNumber().intValue()))))
               .andExpect(jsonPath("$.issues[1].opened", is(issues.get(1).isOpened())))
               .andExpect(jsonPath("$.issues[1].title", is(issues.get(1).getTitle())))
               .andExpect(jsonPath("$.issues[1].createdAt", is(issues.get(1).getCreatedAt().toString())))
               .andExpect(jsonPath("$.issues[1].updatedAt", is(issues.get(1).getUpdatedAt().toString())))
               .andExpect(jsonPath("$.issues[1].author.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1)
                                                                                                                 .getAuthor()
                                                                                                                 .getId()),
                       Matchers.equalTo((Number) issues.get(1).getAuthor().getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].author.userId", is(issues.get(1).getAuthor().getUserId())))
               .andExpect(jsonPath("$.issues[1].author.profileImage", is(issues.get(1).getAuthor().getProfileImage())))
               .andExpect(jsonPath("$.issues[1].assignees", hasSize(issues.get(1).getAssignees().size())))
               .andExpect(jsonPath("$.issues[1].assignees[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getAssignees().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(1).getAssignees().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].assignees[0].userId",
                       is(issues.get(1).getAssignees().get(0).getUserId())))
               .andExpect(jsonPath("$.issues[1].assignees[0].profileImage",
                       is(issues.get(1).getAssignees().get(0).getProfileImage())))
               .andExpect(jsonPath("$.issues[1].assignees[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getAssignees().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(1).getAssignees().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].assignees[1].userId",
                       is(issues.get(1).getAssignees().get(1).getUserId())))
               .andExpect(jsonPath("$.issues[1].assignees[1].profileImage",
                       is(issues.get(1).getAssignees().get(1).getProfileImage())))
               .andExpect(jsonPath("$.issues[1].labels", hasSize(issues.get(1).getLabels().size())))
               .andExpect(jsonPath("$.issues[1].labels[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getLabels().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(1).getLabels().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].labels[0].title", is(issues.get(1).getLabels().get(0).getTitle())))
               .andExpect(jsonPath("$.issues[1].labels[0].color", is(issues.get(1).getLabels().get(0).getColor())))
               .andExpect(jsonPath("$.issues[1].labels[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getLabels().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(1).getLabels().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].labels[1].title", is(issues.get(1).getLabels().get(1).getTitle())))
               .andExpect(jsonPath("$.issues[1].labels[1].color", is(issues.get(1).getLabels().get(1).getColor())))
               .andExpect(jsonPath("$.issues[1].mileStone.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                       1).getMileStone().getId()),
                       Matchers.equalTo((Number) issues.get(1).getMileStone().getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].mileStone.title", is(issues.get(1).getMileStone().getTitle())));
    }
}
