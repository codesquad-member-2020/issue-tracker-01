package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.domain.entity.Issue;
import kr.codesquad.issuetracker.domain.entity.Label;
import kr.codesquad.issuetracker.domain.entity.Milestone;
import kr.codesquad.issuetracker.domain.entity.User;
import kr.codesquad.issuetracker.service.IssueService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
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
        User user1 = User.builder().id(1L).userId("dion").email("dion@codesquad.kr").nickname("Dion").githubToken("abcd").profileImage("image1").build();
        User user2 = User.builder().id(2L).userId("alex").email("alex@codesquad.kr").nickname("Alex").githubToken("good").profileImage("image2").build();

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
                            .milestone(milestone1)
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
                            .milestone(milestone1)
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
                            .milestone(milestone2)
                            .build();

        List<Issue> issues = Stream.of(issue1, issue2, issue3).filter(Issue::isOpened).collect(Collectors.toList());
        when(issueService.findIssues()).thenReturn(issues);

        // then
        mockMvc.perform(get("/issues").contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.issues", hasSize(issues.size())))
               .andExpect(jsonPath("$.issues[0].issueNumber").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getIssueNumber()),
                       Matchers.equalTo((Number) issues.get(0).getIssueNumber().intValue()))))
               .andExpect(jsonPath("$.issues[0].opened", is(issues.get(0).isOpened())))
               .andExpect(jsonPath("$.issues[0].title", is(issues.get(0).getTitle())))
               .andExpect(jsonPath("$.issues[0].createdAt", is(issues.get(0).getCreatedAt().toString())))
               .andExpect(jsonPath("$.issues[0].updatedAt", is(issues.get(0).getUpdatedAt().toString())))
               .andExpect(jsonPath("$.issues[0].author.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getAuthor().getId()),
                       Matchers.equalTo((Number) issues.get(0).getAuthor().getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].author.userId", is(issues.get(0).getAuthor().getUserId())))
               .andExpect(jsonPath("$.issues[0].author.profileImage", is(issues.get(0).getAuthor().getProfileImage())))
               .andExpect(jsonPath("$.issues[0].author.email", is(issues.get(0).getAuthor().getEmail())))
               .andExpect(jsonPath("$.issues[0].author.nickname", is(issues.get(0).getAuthor().getNickname())))
               .andExpect(jsonPath("$.issues[0].author.githubToken", is(issues.get(0).getAuthor().getGithubToken())))
               .andExpect(jsonPath("$.issues[0].assignees", hasSize(issues.get(0).getAssignees().size())))
               .andExpect(jsonPath("$.issues[0].assignees[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getAssignees().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(0).getAssignees().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].assignees[0].userId", is(issues.get(0).getAssignees().get(0).getUserId())))
               .andExpect(jsonPath("$.issues[0].assignees[0].profileImage", is(issues.get(0).getAssignees().get(0).getProfileImage())))
               .andExpect(jsonPath("$.issues[0].assignees[0].email", is(issues.get(0).getAssignees().get(0).getEmail())))
               .andExpect(jsonPath("$.issues[0].assignees[0].nickname", is(issues.get(0).getAssignees().get(0).getNickname())))
               .andExpect(jsonPath("$.issues[0].assignees[0].githubToken", is(issues.get(0).getAssignees().get(0).getGithubToken())))
               .andExpect(jsonPath("$.issues[0].assignees[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getAssignees().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(0).getAssignees().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].assignees[1].userId", is(issues.get(0).getAssignees().get(1).getUserId())))
               .andExpect(jsonPath("$.issues[0].assignees[1].profileImage", is(issues.get(0).getAssignees().get(1).getProfileImage())))
               .andExpect(jsonPath("$.issues[0].assignees[1].email", is(issues.get(0).getAssignees().get(1).getEmail())))
               .andExpect(jsonPath("$.issues[0].assignees[1].nickname", is(issues.get(0).getAssignees().get(1).getNickname())))
               .andExpect(jsonPath("$.issues[0].assignees[1].githubToken", is(issues.get(0).getAssignees().get(1).getGithubToken())))
               .andExpect(jsonPath("$.issues[0].labels", hasSize(issues.get(0).getLabels().size())))
               .andExpect(jsonPath("$.issues[0].labels[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getLabels().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(0).getLabels().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].labels[0].title", is(issues.get(0).getLabels().get(0).getTitle())))
               .andExpect(jsonPath("$.issues[0].labels[0].color", is(issues.get(0).getLabels().get(0).getColor())))
               .andExpect(jsonPath("$.issues[0].milestone.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(0).getMilestone().getId()),
                       Matchers.equalTo((Number) issues.get(0).getMilestone().getId().intValue()))))
               .andExpect(jsonPath("$.issues[0].milestone.title", is(issues.get(0).getMilestone().getTitle())))
               // issue3
               .andExpect(jsonPath("$.issues[1].issueNumber").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getIssueNumber()),
                       Matchers.equalTo((Number) issues.get(1).getIssueNumber().intValue()))))
               .andExpect(jsonPath("$.issues[1].opened", is(issues.get(1).isOpened())))
               .andExpect(jsonPath("$.issues[1].title", is(issues.get(1).getTitle())))
               .andExpect(jsonPath("$.issues[1].createdAt", is(issues.get(1).getCreatedAt().toString())))
               .andExpect(jsonPath("$.issues[1].updatedAt", is(issues.get(1).getUpdatedAt().toString())))
               .andExpect(jsonPath("$.issues[1].author.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getAuthor().getId()),
                       Matchers.equalTo((Number) issues.get(1).getAuthor().getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].author.userId", is(issues.get(1).getAuthor().getUserId())))
               .andExpect(jsonPath("$.issues[1].author.profileImage", is(issues.get(1).getAuthor().getProfileImage())))
               .andExpect(jsonPath("$.issues[1].author.email", is(issues.get(1).getAuthor().getEmail())))
               .andExpect(jsonPath("$.issues[1].author.nickname", is(issues.get(1).getAuthor().getNickname())))
               .andExpect(jsonPath("$.issues[1].author.githubToken", is(issues.get(1).getAuthor().getGithubToken())))
               .andExpect(jsonPath("$.issues[1].assignees", hasSize(issues.get(1).getAssignees().size())))
               .andExpect(jsonPath("$.issues[1].assignees[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getAssignees().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(1).getAssignees().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].assignees[0].userId", is(issues.get(1).getAssignees().get(0).getUserId())))
               .andExpect(jsonPath("$.issues[1].assignees[0].profileImage", is(issues.get(1).getAssignees().get(0).getProfileImage())))
               .andExpect(jsonPath("$.issues[1].assignees[0].email", is(issues.get(1).getAssignees().get(0).getEmail())))
               .andExpect(jsonPath("$.issues[1].assignees[0].nickname", is(issues.get(1).getAssignees().get(0).getNickname())))
               .andExpect(jsonPath("$.issues[1].assignees[0].githubToken", is(issues.get(1).getAssignees().get(0).getGithubToken())))
               .andExpect(jsonPath("$.issues[1].assignees[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getAssignees().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(1).getAssignees().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].assignees[1].userId", is(issues.get(1).getAssignees().get(1).getUserId())))
               .andExpect(jsonPath("$.issues[1].assignees[1].profileImage", is(issues.get(1).getAssignees().get(1).getProfileImage())))
               .andExpect(jsonPath("$.issues[1].assignees[1].email", is(issues.get(1).getAssignees().get(1).getEmail())))
               .andExpect(jsonPath("$.issues[1].assignees[1].nickname", is(issues.get(1).getAssignees().get(1).getNickname())))
               .andExpect(jsonPath("$.issues[1].assignees[1].githubToken", is(issues.get(1).getAssignees().get(1).getGithubToken())))
               .andExpect(jsonPath("$.issues[1].labels", hasSize(issues.get(1).getLabels().size())))
               .andExpect(jsonPath("$.issues[1].labels[0].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getLabels().get(0).getId()),
                       Matchers.equalTo((Number) issues.get(1).getLabels().get(0).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].labels[0].title", is(issues.get(1).getLabels().get(0).getTitle())))
               .andExpect(jsonPath("$.issues[1].labels[0].color", is(issues.get(1).getLabels().get(0).getColor())))
               .andExpect(jsonPath("$.issues[1].labels[1].id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getLabels().get(1).getId()),
                       Matchers.equalTo((Number) issues.get(1).getLabels().get(1).getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].labels[1].title", is(issues.get(1).getLabels().get(1).getTitle())))
               .andExpect(jsonPath("$.issues[1].labels[1].color", is(issues.get(1).getLabels().get(1).getColor())))
               .andExpect(jsonPath("$.issues[1].milestone.id").value(Matchers.anyOf(Matchers.equalTo((Number) issues.get(1).getMilestone().getId()),
                       Matchers.equalTo((Number) issues.get(1).getMilestone().getId().intValue()))))
               .andExpect(jsonPath("$.issues[1].milestone.title", is(issues.get(1).getMilestone().getTitle())))
               .andDo(print())
               .andDo(document("{class-name}/{method-name}",
                       preprocessRequest(prettyPrint()),
                       preprocessResponse(prettyPrint()),
                       responseFields(fieldWithPath("issues").description("이슈의 목록").type(JsonFieldType.ARRAY),
                               fieldWithPath("issues[].issueNumber").description("해당 이슈의 번호").type(JsonFieldType.NUMBER),
                               fieldWithPath("issues[].opened").description("해당 이슈가 열렸는지 여부").type(JsonFieldType.BOOLEAN),
                               fieldWithPath("issues[].title").description("해당 이슈의 제목").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].createdAt").description("해당 이슈의 생성일자").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].updatedAt").description("해당 이슈의 수정일자").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].author").description("해당 이슈의 작성자").type(JsonFieldType.OBJECT),
                               fieldWithPath("issues[].author.id").description("해당 이슈의 작성자의 사용자 고유 id").type(JsonFieldType.NUMBER),
                               fieldWithPath("issues[].author.userId").description("해당 이슈의 작성자의 사용자 계정 Id").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].author.profileImage").description("해당 이슈의 작성자의 프로필 image 주소").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].author.email").description("해당 이슈의 작성자의 email 주소").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].author.nickname").description("해당 이슈의 작성자의 닉네임").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].author.githubToken").description("해당 이슈의 작성자의 github Token 값").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].assignees").description("해당 이슈의 담당자 목록").type(JsonFieldType.ARRAY),
                               fieldWithPath("issues[].assignees[].id").description("해당 이슈의 해당 담당자의 사용자 고유 id").type(JsonFieldType.NUMBER),
                               fieldWithPath("issues[].assignees[].userId").description("해당 이슈의 해당 담당자의 사용자 계정 Id").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].assignees[].profileImage").description("해당 이슈의 해당 담당자의 프로필 image 주소").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].assignees[].email").description("해당 이슈의 해당 담당자의 email 주소").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].assignees[].nickname").description("해당 이슈의 해당 담당자의 닉네임").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].assignees[].githubToken").description("해당 이슈의 해당 담당자의 github Token 값").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].labels[]").description("해당 이슈의 라벨 목록").type(JsonFieldType.ARRAY),
                               fieldWithPath("issues[].labels[].id").description("해당 이슈의 해당 라벨의 고유 id").type(JsonFieldType.NUMBER),
                               fieldWithPath("issues[].labels[].title").description("해당 이슈의 해당 라벨의 타이틀").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].labels[].color").description("해당 이슈의 해당 라벨의 hexcode").type(JsonFieldType.STRING),
                               fieldWithPath("issues[].milestone").description("해당 이슈의 마일스톤").type(JsonFieldType.OBJECT),
                               fieldWithPath("issues[].milestone.id").description("해당 이슈의 마일스톤의 고유 id").type(JsonFieldType.NUMBER),
                               fieldWithPath("issues[].milestone.title").description("해당 이슈의 마일스톤의 타이틀").type(JsonFieldType.STRING))));
    }

    @Test
    @DisplayName("선택한 이슈 일괄 오픈 테스트")
    void 선택한_이슈_일괄_오픈_테스트() throws Exception {
        // given
        // language=JSON
        String jsonString = "{\"issueNumbers\": [1], \"state\": \"open\"}";

        // then
        MockHttpServletRequestBuilder requestBuilder = put("/issues/state").content(jsonString).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", is("성공")));
    }

    @Test
    @DisplayName("선택한 이슈 일괄 클로즈 테스트")
    void 선택한_이슈_일괄_클로즈_테스트() throws Exception {
        // given
        // language=JSON
        String jsonString = "{\"issueNumbers\": [1,2], \"state\": \"close\"}";

        // then
        MockHttpServletRequestBuilder requestBuilder = put("/issues/state").content(jsonString).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.message", is("성공")));
    }
}
