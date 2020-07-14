package kr.codesquad.issuetracker.controller.response;

import java.util.List;
import kr.codesquad.issuetracker.domain.milestone.MilestoneOfMilestoneList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MilestoneListResponse {

  private List<MilestoneOfMilestoneList> milestones;
}
