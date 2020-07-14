package kr.codesquad.issuetracker.common.error.exception.domain.label;

import kr.codesquad.issuetracker.common.error.ErrorCode;
import kr.codesquad.issuetracker.common.error.exception.EntityNotFoundException;

public class LabelNotFoundException extends EntityNotFoundException {

  public LabelNotFoundException() {
    super(ErrorCode.LABEL_NOT_FOUND);
  }

  public LabelNotFoundException(String message) {
    super(message, ErrorCode.LABEL_NOT_FOUND);
  }
}
