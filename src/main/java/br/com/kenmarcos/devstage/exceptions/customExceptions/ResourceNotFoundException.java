package br.com.kenmarcos.devstage.exceptions.customExceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
