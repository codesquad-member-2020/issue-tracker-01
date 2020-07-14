package kr.codesquad.issuetracker.controller.response;

import java.util.List;
import kr.codesquad.issuetracker.domain.label.LabelOfFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SearchFilterLabelResponse {

  private List<LabelOfFilter> labels;

  public int getLabelsCount() {
    return labels.size();
  }
}
