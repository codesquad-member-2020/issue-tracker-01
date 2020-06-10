package kr.codesquad.issuetracker.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MileStoneTest {

    @Test
    @DisplayName("toString method test")
    void toStringMethodTest() {
        assertThat(MileStone.builder().id(1L).title("Phase1").build().toString().contains("*")).isFalse();
    }

    @Test
    @DisplayName("NoArgsConstructor annotation test")
    void noArgsConstructorAnnotationTest() {
        MileStone mileStone = new MileStone();
        assertThat(mileStone.getId()).isNull();
        assertThat(mileStone.getTitle()).isNull();
    }
}
