package edu.filesystem;

public class EndOfFileException extends RuntimeException {

  public EndOfFileException() {
    super();
  }

  /**
   * Constructs a new exception with the specified message.
   */
  public EndOfFileException(String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the specified message and
   * cause. 
   */
  public EndOfFileException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new exception with the specified cause.
   */
  public EndOfFileException(Throwable cause) {
    super(cause);
  }

}
