package kr.codesquad.issuetracker.controller;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.label.LabelOfIssue;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfIssue;
import kr.codesquad.issuetracker.domain.user.UserOfIssue;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.LabelService;
import kr.codesquad.issuetracker.service.MilestoneService;
import kr.codesquad.issuetracker.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


@WebMvcTest(controllers = {IssueController.class})
class IssueControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IssueService issueService;

  @MockBean
  LabelService labelService;

  @MockBean
  UserService userService;

  @MockBean
  MilestoneService milestoneService;

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("오픈된 이슈 조회 검색 테스트")
  void 오픈된_이슈_조회_검색_테스트() throws Exception {
    // given
    LocalDateTime now = LocalDateTime.now();

    // user
    UserOfIssue user1 = UserOfIssue.builder().nickname("dion").profileImage("dion image").build();
    UserOfIssue user2 = UserOfIssue.builder().nickname("reese").profileImage("reese image").build();

    // label
    LabelOfIssue label1 = LabelOfIssue.builder().title("BE").color("#FFFFFF").build();
    LabelOfIssue label2 = LabelOfIssue.builder().title("FE").color("#000000").build();

    // milestone
    MilestoneOfIssue milestone1 = MilestoneOfIssue.builder().title("Phase1").build();
    MilestoneOfIssue milestone2 = MilestoneOfIssue.builder().title("Phase2").build();
    MilestoneOfIssue milestone3 = MilestoneOfIssue.builder().title("Phase3").build();

    // issue
    IssueOfIssueList issue1 = IssueOfIssueList.builder().issueNumber(1L).author(user1)
        .isOpened(true).createdAt(now).updatedAt(now).title("밥을 못먹어서 문제")
        .assignees(asList(user1, user2)).labels(singletonList(label1)).milestone(milestone1)
        .build();
    IssueOfIssueList issue2 = IssueOfIssueList.builder().issueNumber(2L).author(user2)
        .isOpened(false).createdAt(now).updatedAt(now).title("운동을 하고싶어서 문제")
        .assignees(singletonList(user2)).labels(singletonList(label2)).milestone(milestone2)
        .build();
    IssueOfIssueList issue3 = IssueOfIssueList.builder().issueNumber(3L).author(user2)
        .isOpened(true).createdAt(now).updatedAt(now).title("열심히 하고싶어서 문제")
        .assignees(singletonList(user2)).labels(asList(label1, label2)).milestone(milestone3)
        .build();

    List<IssueOfIssueList> issues = asList(issue1, issue3);
    when(issueService.findOpenedIssues()).thenReturn(issues);

    // then
    mockMvc.perform(get("/issues").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.issues", hasSize(issues.size())))
        .andExpect(jsonPath("$.issues[0].issueNumber")
            .value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                0).getIssueNumber()),
                Matchers.equalTo(issues.get(0).getIssueNumber().intValue()))))
        .andExpect(jsonPath("$.issues[0].opened", is(issues.get(0).isOpened())))
        .andExpect(jsonPath("$.issues[0].title", is(issues.get(0).getTitle())))
        .andExpect(
            jsonPath("$.issues[0].author.nickname", is(issues.get(0).getAuthor().getNickname())))
        .andExpect(jsonPath("$.issues[0].author.profileImage",
            is(issues.get(0).getAuthor().getProfileImage())))
        .andExpect(jsonPath("$.issues[0].assignees", hasSize(issues.get(0).getAssignees().size())))
        .andExpect(jsonPath("$.issues[0].assignees[0].profileImage",
            is(issues.get(0).getAssignees().get(0).getProfileImage())))
        .andExpect(jsonPath("$.issues[0].assignees[0].nickname",
            is(issues.get(0).getAssignees().get(0).getNickname())))
        .andExpect(jsonPath("$.issues[0].assignees[1].profileImage",
            is(issues.get(0).getAssignees().get(1).getProfileImage())))
        .andExpect(jsonPath("$.issues[0].assignees[1].nickname",
            is(issues.get(0).getAssignees().get(1).getNickname())))
        .andExpect(jsonPath("$.issues[0].labels", hasSize(issues.get(0).getLabels().size())))
        .andExpect(jsonPath("$.issues[0].labels[0].title",
            is(issues.get(0).getLabels().get(0).getTitle())))
        .andExpect(jsonPath("$.issues[0].labels[0].color",
            is(issues.get(0).getLabels().get(0).getColor())))
        .andExpect(
            jsonPath("$.issues[0].milestone.title", is(issues.get(0).getMilestone().getTitle())))
        // issue3
        .andExpect(jsonPath("$.issues[1].issueNumber")
            .value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(
                1).getIssueNumber()),
                Matchers.equalTo(issues.get(1).getIssueNumber().intValue()))))
        .andExpect(jsonPath("$.issues[1].opened", is(issues.get(1).isOpened())))
        .andExpect(jsonPath("$.issues[1].title", is(issues.get(1).getTitle())))
        .andExpect(jsonPath("$.issues[1].author.profileImage",
            is(issues.get(1).getAuthor().getProfileImage())))
        .andExpect(jsonPath("$.issues[1].author.nickname",
            is(issues.get(1).getAuthor().getNickname())))
        .andExpect(jsonPath("$.issues[1].assignees", hasSize(issues.get(1).getAssignees().size())))
        .andExpect(jsonPath("$.issues[1].assignees[0].profileImage",
            is(issues.get(1).getAssignees().get(0).getProfileImage())))
        .andExpect(jsonPath("$.issues[1].assignees[0].nickname",
            is(issues.get(1).getAssignees().get(0).getNickname())))
        .andExpect(jsonPath("$.issues[1].labels", hasSize(issues.get(1).getLabels().size())))
        .andExpect(jsonPath("$.issues[1].labels[0].title",
            is(issues.get(1).getLabels().get(0).getTitle())))
        .andExpect(jsonPath("$.issues[1].labels[0].color",
            is(issues.get(1).getLabels().get(0).getColor())))
        .andExpect(jsonPath("$.issues[1].labels[1].title",
            is(issues.get(1).getLabels().get(1).getTitle())))
        .andExpect(jsonPath("$.issues[1].labels[1].color",
            is(issues.get(1).getLabels().get(1).getColor())))
        .andExpect(
            jsonPath("$.issues[1].milestone.title", is(issues.get(1).getMilestone().getTitle())));
  }

  @Test
  @DisplayName("선택한 이슈 일괄 오픈 테스트")
  void 선택한_이슈_일괄_오픈_테스트() throws Exception {
    // given
    IssuesOpenStatusChangeRequest request = new IssuesOpenStatusChangeRequest();
    request.setState("open");
    request.setIssueNumbers(singletonList(1L));
    when(issueService.updateIssuesOpenStatus(any(IssuesOpenStatusChangeRequest.class)))
        .thenReturn(true);

    // then
    MockHttpServletRequestBuilder requestBuilder =
        put("/issues/state").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.message", is("성공")));
  }

  @Test
  @DisplayName("선택한 이슈 일괄 클로즈 테스트")
  void 선택한_이슈_일괄_클로즈_테스트() throws Exception {
    // given
    IssuesOpenStatusChangeRequest request = new IssuesOpenStatusChangeRequest();
    request.setState("close");
    request.setIssueNumbers(asList(1L, 2L));
    when(issueService.updateIssuesOpenStatus(any(IssuesOpenStatusChangeRequest.class)))
        .thenReturn(true);

    // then
    MockHttpServletRequestBuilder requestBuilder =
        put("/issues/state").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.message", is("성공")));
  }
}
