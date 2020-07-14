package kr.codesquad.issuetracker.common.error.exception;

import kr.codesquad.issuetracker.common.error.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(message, ErrorCode.USER_NOT_FOUND);
  }
}
