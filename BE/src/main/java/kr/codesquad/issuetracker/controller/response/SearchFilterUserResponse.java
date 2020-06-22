package kr.codesquad.issuetracker.controller.response;

import java.util.List;
import kr.codesquad.issuetracker.domain.user.UserOfFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SearchFilterUserResponse {

  private List<UserOfFilter> users;
}
