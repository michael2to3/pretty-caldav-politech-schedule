package pretty.schedule.scheme;

/** Error thrown when a schedule is invalid. */
public class ErrorResponse {
  /** Error code. */
  private String status;
  /** Error message. */
  private String message;

  public ErrorResponse(final String status, final String message) {
    this.status = status;
    this.message = message;
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
