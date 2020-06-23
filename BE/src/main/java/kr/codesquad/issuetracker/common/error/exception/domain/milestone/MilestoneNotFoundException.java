package kr.codesquad.issuetracker.common.error.exception.domain.milestone;

import kr.codesquad.issuetracker.common.error.ErrorCode;
import kr.codesquad.issuetracker.common.error.exception.EntityNotFoundException;

public class MilestoneNotFoundException extends EntityNotFoundException {

  public MilestoneNotFoundException() {
    super(ErrorCode.MILESTONE_NOT_FOUND);
  }

  public MilestoneNotFoundException(String message) {
    super(message, ErrorCode.MILESTONE_NOT_FOUND);
  }
}
