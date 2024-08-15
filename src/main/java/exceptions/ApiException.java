package exceptions;

import java.io.IOException;

public class ApiException extends Exception {

  public ApiException(String message, IOException cause) {
    super(message, cause);
  }
}
