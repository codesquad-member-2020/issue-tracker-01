package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.domain.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {IssueController.class})
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("오픈된 이슈 조회 검색 테스트")
    void 오픈된_이슈_조회_검색_테스트() {
        // given
        Issue issue1 = Issue.builder().isOpened(true).build();
        Issue issue2 = Issue.builder().isOpened(false).build();

        // then
    }
}
