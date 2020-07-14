package kr.codesquad.issuetracker.controller.response;

import java.util.List;
import kr.codesquad.issuetracker.domain.label.LabelOfLabelList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LabelListResponse {

  private List<LabelOfLabelList> labels;
}
