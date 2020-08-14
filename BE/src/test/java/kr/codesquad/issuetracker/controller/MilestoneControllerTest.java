package kr.codesquad.issuetracker.controller;

import static kr.codesquad.issuetracker.common.constant.CommonConstant.HOST;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.Cookie;
import kr.codesquad.issuetracker.controller.request.MilestoneRequest;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfMilestoneList;
import kr.codesquad.issuetracker.domain.user.User;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.MilestoneService;
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
@WebMvcTest(controllers = {MilestoneController.class})
class MilestoneControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  MilestoneService milestoneService;

  @MockBean
  JwtService jwtService;

  String jwt = "jwt";
  UserDTO userDTO = UserDTO
      .of(User.builder().id(1L).userId("jwtUser").email("jwt@idion.dev").nickname("jwt").build());
  Cookie cookie = new Cookie("jwt", jwt);

  public static String asJsonString(final Object obj) {
    try {
      // 시간을 serialize 할 때 문제를 해결합니다.
      // https://stackoverflow.com/questions/28802544/java-8-localdate-jackson-format
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
      ;
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  void setUp() {
    when(jwtService.getUserFromJws(jwt)).thenReturn(userDTO);
  }

  @Test
  @DisplayName("Milestone 목록 조회 테스트")
  void milestone_목록_조회_테스트() throws Exception {
    // given
    MilestoneOfMilestoneList milestoneDTO = MilestoneOfMilestoneList.builder()
        .id(1L)
        .title("Test")
        .description("설명")
        .isOpened(true)
        .lastUpdatedDate(LocalDateTime.now())
        .dueDate(LocalDate.now())
        .openIssueCount(2L)
        .closedIssueCount(2L)
        .completeRatio(50)
        .build();
    List<MilestoneOfMilestoneList> milestoneList = Collections.singletonList(milestoneDTO);

    when(milestoneService.findAll()).thenReturn(milestoneList);

    // then
    mockMvc.perform(get("/milestones").header("Origin", "*").contentType(MediaType.APPLICATION_JSON)
        .cookie(cookie))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.milestones", hasSize(milestoneList.size())))
        .andExpect(jsonPath("$.milestones[0].id").value(Matchers.anyOf(
            Matchers.equalTo((Number) milestoneList.get(0).getId()),
            Matchers.equalTo(milestoneList.get(0).getId().intValue())
        )))
        .andExpect(jsonPath("$.milestones[0].title", is(milestoneList.get(0).getTitle())))
        .andExpect(
            jsonPath("$.milestones[0].description", is(milestoneList.get(0).getDescription())))
        .andExpect(jsonPath("$.milestones[0].opened", is(milestoneList.get(0).isOpened())))
        .andExpect(jsonPath("$.milestones[0].openIssueCount").value(Matchers.anyOf(
            Matchers.equalTo((Number) milestoneList.get(0).getOpenIssueCount()),
            Matchers.equalTo(milestoneList.get(0).getOpenIssueCount().intValue())
        )))
        .andExpect(jsonPath("$.milestones[0].closedIssueCount").value(Matchers.anyOf(
            Matchers.equalTo((Number) milestoneList.get(0).getClosedIssueCount()),
            Matchers.equalTo(milestoneList.get(0).getClosedIssueCount().intValue())
        )))
        .andExpect(
            jsonPath("$.milestones[0].completeRatio", is(milestoneList.get(0).getCompleteRatio())))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath("milestones").description("마일스톤의 목록").type(JsonFieldType.ARRAY),
                fieldWithPath("milestones[].id").description("마일스톤의 고유 id")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("milestones[].title").description("마일스톤의 타이틀")
                    .type(JsonFieldType.STRING),
                fieldWithPath("milestones[].description").description("마일스톤에 대한 설명")
                    .type(JsonFieldType.STRING),
                fieldWithPath("milestones[].dueDate").description("마일스톤의 데드라인")
                    .type(JsonFieldType.STRING),
                fieldWithPath("milestones[].lastUpdatedDate").description("마일스톤의 최종 수정 일자")
                    .type(JsonFieldType.STRING),
                fieldWithPath("milestones[].openIssueCount").description("마일스톤의 열려있는 issue 개수")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("milestones[].closedIssueCount").description("마일스톤의 닫힌 issue 개수")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("milestones[].completeRatio").description("마일스톤의 완료율(%)")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("milestones[].opened").description("마일스톤의 Open 여부")
                    .type(JsonFieldType.BOOLEAN)
            )));
  }

  @Test
  @DisplayName("Milestone 생성 테스트")
  void milestone_생성_테스트() throws Exception {
    // given
    MilestoneRequest milestoneRequest = new MilestoneRequest();
    milestoneRequest.setTitle("생성 테스트");
    milestoneRequest.setDescription("생성을 하려면 이렇게 하세요.");
    milestoneRequest.setDueDate(LocalDate.now());

    Long createdId = 1L;
    when(milestoneService.createMilestone(any(MilestoneRequest.class))).thenReturn(createdId);

    // then
    MockHttpServletRequestBuilder requestBuilder = post("/milestones")
        .contentType(MediaType.APPLICATION_JSON)
        .header("Origin", "*")
        .characterEncoding("UTF-8")
        .content(asJsonString(milestoneRequest))
        .cookie(cookie);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(redirectedUrl(HOST + "/milestones/" + createdId.intValue()))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(
                fieldWithPath("title").description("milestone의 타이틀").type(JsonFieldType.STRING),
                fieldWithPath("description").description("milestone의 설명")
                    .type(JsonFieldType.STRING),
                fieldWithPath("dueDate").description("milestone의 데드라인(yyyy-MM-dd)")
                    .type(JsonFieldType.STRING)
            )));
  }
}
