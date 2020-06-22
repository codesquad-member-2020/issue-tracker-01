package kr.codesquad.issuetracker.common.error.exception;

import kr.codesquad.issuetracker.common.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

  public EntityNotFoundException(String message) {
    super(message, ErrorCode.ENTITY_NOT_FOUND);
  }

  public EntityNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public EntityNotFoundException(String message, ErrorCode errorCode) {
    super(message, errorCode);
  }
}
