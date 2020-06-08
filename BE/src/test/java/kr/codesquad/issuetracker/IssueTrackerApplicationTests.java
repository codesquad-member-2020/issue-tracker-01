package kr.codesquad.issuetracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IssueTrackerApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(IssueTrackerApplicationTests.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("main Method Test")
    void main() {
        IssueTrackerApplication.main(new String[]{});
    }

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void loggerLoads() {
        assertThat(log).isNotNull();
    }
}
