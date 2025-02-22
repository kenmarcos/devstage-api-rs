package br.com.kenmarcos.devstage.exceptions.customExceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
  public ResourceAlreadyExistsException(String message) {
    super(message);
  }
}
