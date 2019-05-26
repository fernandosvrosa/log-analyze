package br.com.log.analyze.domain.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GameLine {

  private String gameName;
  private List<String> lines = new ArrayList<>();


  public void addLine(String line) {
    this.lines.add(line);
  }
}
