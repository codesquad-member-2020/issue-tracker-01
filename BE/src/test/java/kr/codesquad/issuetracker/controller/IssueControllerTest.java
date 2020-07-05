package kr.codesquad.issuetracker.controller;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import kr.codesquad.issuetracker.controller.request.IssuesOpenStatusChangeRequest;
import kr.codesquad.issuetracker.controller.response.IssueDetail;
import kr.codesquad.issuetracker.domain.comment.CommentOfIssue;
import kr.codesquad.issuetracker.domain.comment.ImageOfComment;
import kr.codesquad.issuetracker.domain.issue.IssueOfIssueList;
import kr.codesquad.issuetracker.domain.label.LabelOfIssue;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfIssue;
import kr.codesquad.issuetracker.domain.user.User;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.domain.user.UserOfIssue;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.LabelService;
import kr.codesquad.issuetracker.service.MilestoneService;
import kr.codesquad.issuetracker.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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

@AutoConfigureRestDocs(uriHost = "13.124.148.192/api", uriPort = 80)
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

  @MockBean
  JwtService jwtService;

  String jwt = "jwt";
  UserDTO userDTO = UserDTO
      .of(User.builder().id(1L).userId("jwtUser").email("jwt@idion.dev").nickname("jwt").build());

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  void setUp() {
    when(jwtService.getUserFromJws(jwt)).thenReturn(userDTO);
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
    mockMvc.perform(
        get("/issues").contentType(MediaType.APPLICATION_JSON).cookie(new Cookie("jwt", jwt)))
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
            jsonPath("$.issues[1].milestone.title", is(issues.get(1).getMilestone().getTitle())))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath("issues").description("이슈의 목록").type(JsonFieldType.ARRAY),
                fieldWithPath("issues[].issueNumber").description("이슈의 번호(고유한 값)")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("issues[].title").description("이슈의 제목").type(JsonFieldType.STRING),
                fieldWithPath("issues[].createdAt").description("이슈 생성일시 (yyyy-MM-dd hh:mm:ss)")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].updatedAt").description("이슈 수정일시 (yyyy-MM-dd hh:mm:ss)")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].author").description("이슈 작성자").type(JsonFieldType.OBJECT),
                fieldWithPath("issues[].author.nickname").description("이슈 작성자의 닉네임")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].author.profileImage").description("이슈 작성자의 프로필 이미지 주소")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].assignees").description("이슈 담당자 목록")
                    .type(JsonFieldType.ARRAY),
                fieldWithPath("issues[].assignees[].nickname").description("이슈 담당자의 닉네임")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].assignees[].profileImage").description("이슈 담당자의 프로필 이미지 주소")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].labels").description("이슈에 해당하는 라벨 목록")
                    .type(JsonFieldType.ARRAY),
                fieldWithPath("issues[].labels[].title").description("라벨의 타이틀")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].labels[].color").description("라벨의 배경 컬러(Hex Code)")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].milestone").description("이슈의 마일스톤")
                    .type(JsonFieldType.OBJECT),
                fieldWithPath("issues[].milestone.title").description("이슈의 마일스톤 타이틀")
                    .type(JsonFieldType.STRING),
                fieldWithPath("issues[].opened").description("이슈가 Open 되어있는지의 여부")
                    .type(JsonFieldType.BOOLEAN)
            )));
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
        put("/issues/state").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
            .cookie(new Cookie("jwt", jwt));
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.message", is("성공")))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())));
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
        put("/issues/state").content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
            .cookie(new Cookie("jwt", jwt));
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.message", is("성공")))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())));
  }

  @Test
  @DisplayName("이슈 상세정보 보기 테스트")
  void 이슈_상세정보_보기_테스트() throws Exception {
    // given
    Long issueNumber = 1L;

    LocalDateTime now = LocalDateTime.now();

    // user
    UserOfIssue user1 = UserOfIssue.builder().nickname("dion").profileImage("dion image").build();
    UserOfIssue user2 = UserOfIssue.builder().nickname("reese").profileImage("reese image").build();

    // image
    ImageOfComment image = ImageOfComment.builder().url("image url").build();

    // comment
    CommentOfIssue comment1 = CommentOfIssue.builder().description("이슈 만들었어요.")
        .images(Arrays.asList(image)).writer(user1).createdAt(now).updatedAt(now).build();

    // label
    LabelOfIssue label1 = LabelOfIssue.builder().title("BE").color("#FFFFFF").build();

    // milestone
    MilestoneOfIssue milestone1 = MilestoneOfIssue.builder().title("Phase1").build();

    // issue
    IssueDetail issue = IssueDetail.builder()
        .issueNumber(issueNumber)
        .assignees(asList(user1, user2))
        .author(user1)
        .comments(asList(comment1))
        .isOpened(true)
        .labels(asList(label1))
        .milestone(milestone1)
        .title("이슈 생성 테스트입니다.")
        .createdAt(now)
        .updatedAt(now)
        .build();
    when(issueService.findIssueDetail(issueNumber)).thenReturn(issue);

    // then
    MockHttpServletRequestBuilder requestBuilder = get("/issues/" + issueNumber.intValue())
        .contentType(MediaType.APPLICATION_JSON)
        .cookie(new Cookie("jwt", this.jwt));
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.issueNumber").value(Matchers
            .anyOf(Matchers.equalTo((Number) issue.getIssueNumber()),
                Matchers.equalTo(issue.getIssueNumber().intValue()))))
        .andExpect(jsonPath("$.title", is(issue.getTitle())))
        .andExpect(jsonPath("$.author.nickname", is(issue.getAuthor().getNickname())))
        .andExpect(jsonPath("$.author.profileImage", is(issue.getAuthor().getProfileImage())))
        .andExpect(jsonPath("$.assignees", hasSize(issue.getAssignees().size())))
        .andExpect(jsonPath("$.assignees[0].nickname",
            is(issue.getAssignees().get(0).getNickname())))
        .andExpect(jsonPath("$.assignees[0].profileImage",
            is(issue.getAssignees().get(0).getProfileImage())))
        .andExpect(jsonPath("$.assignees[1].nickname",
            is(issue.getAssignees().get(1).getNickname())))
        .andExpect(jsonPath("$.assignees[1].profileImage",
            is(issue.getAssignees().get(1).getProfileImage())))
        .andExpect(jsonPath("$.labels", hasSize(issue.getLabels().size())))
        .andExpect(jsonPath("$.labels[0].title", is(issue.getLabels().get(0).getTitle())))
        .andExpect(jsonPath("$.labels[0].color", is(issue.getLabels().get(0).getColor())))
        .andExpect(jsonPath("$.milestone.title", is(issue.getMilestone().getTitle())))
        .andExpect(jsonPath("$.comments", hasSize(issue.getComments().size())))
        .andExpect(jsonPath("$.comments[0].description",
            is(issue.getComments().get(0).getDescription())))
        .andExpect(jsonPath("$.comments[0].images",
            hasSize(issue.getComments().get(0).getImages().size())))
        .andExpect(jsonPath("$.comments[0].images[0].url",
            is(issue.getComments().get(0).getImages().get(0).getUrl())))
        .andExpect(jsonPath("$.comments[0].writer.nickname",
            is(issue.getComments().get(0).getWriter().getNickname())))
        .andExpect(jsonPath("$.comments[0].writer.profileImage",
            is(issue.getComments().get(0).getWriter().getProfileImage())))
        .andExpect(jsonPath("$.opened", is(issue.isOpened())));
  }
}
