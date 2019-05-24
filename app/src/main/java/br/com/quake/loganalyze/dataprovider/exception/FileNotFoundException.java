package br.com.quake.loganalyze.dataprovider.exception;

import java.io.IOException;

public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException(String error, Exception e) {
    super(error, e);
  }
}
