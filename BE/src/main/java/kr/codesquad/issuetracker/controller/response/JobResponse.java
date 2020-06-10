package kr.codesquad.issuetracker.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class JobResponse {

    private final boolean success;
    private final String message;
}
