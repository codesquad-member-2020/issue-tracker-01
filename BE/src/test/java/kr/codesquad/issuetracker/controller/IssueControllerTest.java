package kr.codesquad.issuetracker.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.fail;

@WebMvcTest(controllers = {IssueController.class})
public class IssueControllerTest {

    private static final Logger log = LoggerFactory.getLogger(IssueControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("오픈된 이슈 조회 검색 테스트")
    void 오픈된_이슈_조회_검색_테스트() {
        fail("Not Implemented");
    }
}
