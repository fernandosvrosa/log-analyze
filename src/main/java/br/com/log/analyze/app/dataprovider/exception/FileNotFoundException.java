package br.com.log.analyze.app.dataprovider.exception;

public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException(String error, Exception e) {
    super(error, e);
  }
}
