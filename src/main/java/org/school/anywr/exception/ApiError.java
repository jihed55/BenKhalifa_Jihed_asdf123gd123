package org.school.anywr.exception;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiError {
  private HttpStatus status;
  private OffsetDateTime timestamp;
  private String message;

  public ApiError(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
    this.timestamp = OffsetDateTime.now();
  }
}
