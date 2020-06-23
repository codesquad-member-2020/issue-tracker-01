package kr.codesquad.issuetracker.controller.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class MilestoneRequest {

  private String title;
  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDate;
}
