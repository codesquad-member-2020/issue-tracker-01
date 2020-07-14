package kr.codesquad.issuetracker.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  ENTITY_NOT_FOUND(404, "COM001", " Entity Not Found"),
  INVALID_TYPE_VALUE(400, "COM002", "Wrong Type"),
  INVALID_INPUT_VALUE(400, "COM003", "Wrong InputValue"),
  METHOD_NOT_ALLOWED(405, "COM004", "Change Http Method"),
  INTERNAL_SERVER_ERROR(500, "COM005", "백엔드 개발자라 죄송합니다..."),

  // user
  USER_NOT_FOUND(404, "U001", " 해당 사용자가 존재하지 않습니다."),
  LOGIN_REQUIRED(401, "U002", " 로그인을 해주세요."),

  // domain
  LABEL_NOT_FOUND(404, "L001", "해당 Label은 존재하지 않는 Label입니다."),
  MILESTONE_NOT_FOUND(404, "M001", "해당 Milestone은 존재하지 않는 Milestone입니다."),
  ;

  private final int status;
  private final String code;
  private final String message;
}
