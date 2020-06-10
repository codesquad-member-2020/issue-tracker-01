package kr.codesquad.issuetracker.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class IssuesOpenStatusChangeRequest {

    private List<Long> issueNumbers;
    private String state;
}
