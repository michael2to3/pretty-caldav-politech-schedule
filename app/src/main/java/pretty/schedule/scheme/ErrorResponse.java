package pretty.schedule.scheme;

/** Error thrown when a schedule is invalid. */
public class ErrorResponse {
  /** Error code. */
  private String status;
  /** Error message. */
  private String message;
  /** Error details (e.g. stack trace). */
  private String details;

  public ErrorResponse() {
  }

  public ErrorResponse(final String status, final String message) {
    this.status = status;
    this.message = message;
  }

  public ErrorResponse(final String status, final String message, final String details) {
    this.status = status;
    this.message = message;
    this.details = details;
  }

  @Override
  public String toString() {
    if (details == null) {
      return String.format("ErrorResponse[status=%s, message=%s]", status, message);
    }
    return String.format("ErrorResponse[status=%s, message=%s, details=%s]", status, message, details);
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
