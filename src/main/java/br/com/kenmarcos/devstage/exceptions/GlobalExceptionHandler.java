package br.com.kenmarcos.devstage.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.kenmarcos.devstage.exceptions.dtos.ErrorMessageDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource message) {
    this.messageSource = message;
  } 

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<ErrorMessageDTO> errorsDto = new ArrayList<>();

    ex.getBindingResult().getFieldErrors().forEach(err -> {
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
      errorsDto.add(error);
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsDto);
  }
}
