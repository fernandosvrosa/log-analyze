package br.com.log.analyze.domain.usecase.parser;

import br.com.log.analyze.domain.entity.Game;

public interface ParserGame {

  boolean isThisLine(String line);

  void execute(String line, Game game);

}
