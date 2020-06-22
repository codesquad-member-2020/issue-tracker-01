package kr.codesquad.issuetracker.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import kr.codesquad.issuetracker.domain.user.UserOfFilter;
import kr.codesquad.issuetracker.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public List<UserOfFilter> findUsersByFilteringKeyword(String keyword) {
    keyword = changeNullToEmptyString(keyword);

    return userRepository.findByFilteringKeyword(keyword)
        .stream()
        .map(UserOfFilter::new)
        .collect(toList());
  }

  private String changeNullToEmptyString(String keyword) {
    if (keyword == null) {
      keyword = "";
    }
    return keyword;
  }
}
