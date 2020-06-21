package kr.codesquad.issuetracker.common.exception;

public class UserNotFoundException extends EntityNotFoundException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(message, ErrorCode.USER_NOT_FOUND);
  }
}
