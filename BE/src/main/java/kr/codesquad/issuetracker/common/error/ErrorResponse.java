package kr.codesquad.issuetracker.common.error;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ErrorResponse {

  private final int status;
  private final String message;
  private final String code;
  private final List<FieldError> errors;

  private ErrorResponse(final ErrorCode code) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.code = code.getCode();
    this.errors = new ArrayList<>();
  }

  private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
    this.status = code.getStatus();
    this.message = code.getMessage();
    this.code = code.getCode();
    this.errors = errors;
  }

  public static ErrorResponse of(final ErrorCode code) {
    return new ErrorResponse(code);
  }

  public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
    return new ErrorResponse(code, FieldError.of(bindingResult));
  }

  public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
    return new ErrorResponse(code, errors);
  }

  @Getter
  public static class FieldError {

    private String field;
    private String value;
    private String reason;

    private FieldError(final String field, final Object value, final String reason) {
      this.field = field;
      this.value = value == null ? "" : value.toString();
      this.reason = reason;
    }

    public static List<FieldError> of(final String field, final String value, final String reason) {
      List<FieldError> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldError(field, value, reason));
      return fieldErrors;
    }

    public static FieldError with(org.springframework.validation.FieldError error) {
      return new FieldError(error.getField(), error.getRejectedValue(), error.getDefaultMessage());
    }

    private static List<FieldError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors =
          bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(FieldError::with)
          .collect(Collectors.toList());
    }
  }
}
