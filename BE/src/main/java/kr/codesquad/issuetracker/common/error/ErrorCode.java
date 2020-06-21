package kr.codesquad.issuetracker.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
  ENTITY_NOT_FOUND(404, "COM001", " Entity Not Found"),
  INVALID_TYPE_VALUE(400, "COM002", "Wrong Type"),
  INVALID_INPUT_VALUE(400, "COM003", "Wrong InputValue"),
  METHOD_NOT_ALLOWED(405, "COM004", "Change Http Method"),
  INTERNAL_SERVER_ERROR(500, "COM005", "백엔드 개발자라 죄송합니다..."),

  USER_NOT_FOUND(404, "U001", " 해당 사용자가 존재하지 않습니다."),
  LOGIN_REQUIRED(401, "U002", " 로그인을 해주세요."),
  ;

  private final String code;
  private final String message;
  private final int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
