package kr.codesquad.issuetracker.controller;

import static kr.codesquad.issuetracker.common.constant.CommonConstant.HOST;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import kr.codesquad.issuetracker.controller.request.LabelRequest;
import kr.codesquad.issuetracker.domain.label.LabelOfLabelList;
import kr.codesquad.issuetracker.domain.user.User;
import kr.codesquad.issuetracker.domain.user.UserDTO;
import kr.codesquad.issuetracker.service.JwtService;
import kr.codesquad.issuetracker.service.LabelService;
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
@WebMvcTest(controllers = {LabelController.class})
class LabelControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  LabelService labelService;

  @MockBean
  UserService userService;

  @MockBean
  JwtService jwtService;

  String jwt = "jwt";
  UserDTO userDTO = UserDTO
      .of(User.builder().id(1L).userId("jwtUser").email("jwt@idion.dev").nickname("jwt").build());
  Cookie cookie = new Cookie("jwt", jwt);

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
  @DisplayName("Label 목록 조회 테스트")
  void label_목록_조회_테스트() throws Exception {
    // given
    LabelOfLabelList labelDTO1 = LabelOfLabelList.builder().id(1L).title("Help").color("#123456")
        .description("도와주세요.").openedIssueCount(3).build();
    LabelOfLabelList labelDTO2 = LabelOfLabelList.builder().id(2L).title("Test").color("#443322")
        .description("테스트코드").openedIssueCount(4).build();

    List<LabelOfLabelList> labels = Arrays.asList(labelDTO1, labelDTO2);
    when(labelService.findLabelsByQueryString(null)).thenReturn(labels);

    // then
    mockMvc.perform(
        get("/labels").contentType(MediaType.APPLICATION_JSON).cookie(cookie))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.labels", hasSize(labels.size())))
        .andExpect(jsonPath("$.labels[0].id").value(Matchers.anyOf(
            Matchers.equalTo((Number) labels.get(0).getId()),
            Matchers.equalTo(labels.get(0).getId().intValue()))))
        .andExpect(jsonPath("$.labels[0].title", is(labels.get(0).getTitle())))
        .andExpect(jsonPath("$.labels[0].color", is(labels.get(0).getColor())))
        .andExpect(jsonPath("$.labels[0].description", is(labels.get(0).getDescription())))
        .andExpect(jsonPath("$.labels[0].openedIssueCount").value(Matchers.anyOf(
            Matchers.equalTo(labels.get(0).getOpenedIssueCount()),
            Matchers.equalTo(labels.get(0).getOpenedIssueCount().intValue()))))
        .andExpect(jsonPath("$.labels[1].id").value(Matchers.anyOf(
            Matchers.equalTo((Number) labels.get(1).getId()),
            Matchers.equalTo(labels.get(1).getId().intValue()))))
        .andExpect(jsonPath("$.labels[1].title", is(labels.get(1).getTitle())))
        .andExpect(jsonPath("$.labels[1].color", is(labels.get(1).getColor())))
        .andExpect(jsonPath("$.labels[1].description", is(labels.get(1).getDescription())))
        .andExpect(jsonPath("$.labels[1].openedIssueCount").value(Matchers.anyOf(
            Matchers.equalTo(labels.get(1).getOpenedIssueCount()),
            Matchers.equalTo(labels.get(1).getOpenedIssueCount().intValue()))))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestParameters(parameterWithName("title").optional().description("검색할 라벨의 title")),
            responseFields(
                fieldWithPath("labels").description("라벨의 목록").type(JsonFieldType.ARRAY),
                fieldWithPath("labels[].id").description("라벨의 고유 id").type(JsonFieldType.NUMBER),
                fieldWithPath("labels[].title").description("라벨의 표기명").type(JsonFieldType.STRING),
                fieldWithPath("labels[].color").description("라벨의 배경 컬러(Hex Code)")
                    .type(JsonFieldType.STRING),
                fieldWithPath("labels[].description").description("라벨 설명")
                    .type(JsonFieldType.STRING),
                fieldWithPath("labels[].openedIssueCount")
                    .description("라벨에 속한 Issue중 열려있는 Issue의 개수").type(JsonFieldType.NUMBER)
            )));
  }

  @Test
  @DisplayName("Label 생성 테스트")
  void label_생성_테스트() throws Exception {
    // given
    LabelRequest labelRequest = new LabelRequest();
    labelRequest.setTitle("타이틀");
    labelRequest.setColor("#AAAAAA");
    labelRequest.setDescription("설명");

    Long createdLabelId = 1L;
    when(labelService.createLabel(any(LabelRequest.class))).thenReturn(createdLabelId);

    // then
    MockHttpServletRequestBuilder requestBuilder = post("/labels")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(asJsonString(labelRequest))
        .cookie(cookie);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(redirectedUrl(HOST + "/labels/" + createdLabelId.intValue()))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(
                fieldWithPath("title").description("Label의 타이틀"),
                fieldWithPath("color").description("Label의 배경 컬러(Hex code)"),
                fieldWithPath("description").description("Label에 대한 설명")
            )))
    ;
  }

  @Test
  @DisplayName("Label 수정 테스트")
  void label_수정_테스트() throws Exception {
    // given
    long labelId = 1L;
    LabelRequest labelRequest = new LabelRequest();
    labelRequest.setTitle("수정할 타이틀");
    labelRequest.setColor("#AAAAAA");
    labelRequest.setDescription("설명");

    when(labelService.updateLabel(any(Long.class), any(LabelRequest.class))).thenReturn(true);

    // then
    MockHttpServletRequestBuilder requestBuilder = put("/labels/{id}", labelId)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(asJsonString(labelRequest))
        .cookie(this.cookie);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success", is(true)))
        .andExpect(jsonPath("$.message", is("성공")))
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("라벨의 id")
            ),
            requestFields(
                fieldWithPath("title").description("Label의 타이틀"),
                fieldWithPath("color").description("Label의 배경 컬러(Hex code)"),
                fieldWithPath("description").description("Label에 대한 설명")
            ),
            responseFields(
                fieldWithPath("success").description("성공 여부").type(JsonFieldType.BOOLEAN),
                fieldWithPath("message").description("정보성 메시지").type(JsonFieldType.STRING)
            )));
  }

  @Test
  @DisplayName("Label 삭제 테스트")
  void label_삭제_테스트() throws Exception {
    // given
    Long labelId = 1L;

    // then
    MockHttpServletRequestBuilder requestBuilder = delete("/labels/{id}", labelId)
        .contentType(MediaType.APPLICATION_JSON)
        .cookie(this.cookie);
    mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNoContent())
        .andDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("라벨의 id")
            )));
  }
}
