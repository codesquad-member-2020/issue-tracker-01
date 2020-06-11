package kr.codesquad.issuetracker.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MilestoneTest {

    @Test
    @DisplayName("toString method test")
    void toStringMethodTest() {
        assertThat(Milestone.builder().id(1L).title("Phase1").build().toString().contains("*")).isFalse();
    }

    @Test
    @DisplayName("NoArgsConstructor annotation test")
    void noArgsConstructorAnnotationTest() {
        Milestone mileStone = new Milestone();
        assertThat(mileStone.getId()).isNull();
        assertThat(mileStone.getTitle()).isNull();
    }
}
