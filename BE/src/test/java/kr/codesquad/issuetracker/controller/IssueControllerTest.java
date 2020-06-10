package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.domain.Issue;
import kr.codesquad.issuetracker.service.IssueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Issue issue1 = Issue.builder().isOpened(true).build();
        Issue issue2 = Issue.builder().isOpened(false).build();
        Issue issue3 = Issue.builder().isOpened(true).build();

        List<Issue> issues = Stream.of(issue1, issue2, issue3).filter(Issue::isOpened).collect(Collectors.toList());
        when(issueService.findIssues()).thenReturn(issues);

        // then
        mockMvc.perform(get("/issues").contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.issues", hasSize(issues.size())))
               .andExpect(jsonPath("$.issues[0].opened", is(true)))
               .andExpect(jsonPath("$.issues[1].opened", is(true)));
    }
}
