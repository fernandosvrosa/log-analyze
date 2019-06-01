package br.com.log.analyze.app.config.http;

import br.com.log.analyze.app.dataprovider.exception.FileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviceConfig {

  @ExceptionHandler(FileNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(final FileNotFoundException ex) {
    return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
  }

  @Data
  @AllArgsConstructor
  private
  class ErrorMessage {

    private String message;
  }
}
