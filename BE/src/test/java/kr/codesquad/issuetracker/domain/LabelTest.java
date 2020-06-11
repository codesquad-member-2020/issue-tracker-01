package kr.codesquad.issuetracker.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LabelTest {

    @Test
    @DisplayName("toString method test")
    void toStringMethodTest() {
        assertThat(Label.builder().id(1L).color("#FFFFFF").title("BE").build().toString().contains("*")).isFalse();
    }

    @Test
    @DisplayName("NoArgsConstructor annotation test")
    void noArgsConstructorAnnotationTest() {
        Label label = new Label();
        assertThat(label.getId()).isNull();
        assertThat(label.getColor()).isNull();
        assertThat(label.getTitle()).isNull();
    }
}
