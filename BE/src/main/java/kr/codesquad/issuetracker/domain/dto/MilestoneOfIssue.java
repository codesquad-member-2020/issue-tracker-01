package kr.codesquad.issuetracker.domain.dto;

import kr.codesquad.issuetracker.domain.entity.Milestone;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MilestoneOfIssue {

    private String title;

    public MilestoneOfIssue(Milestone milestone) {
        this.title = milestone.getTitle();
    }
}
