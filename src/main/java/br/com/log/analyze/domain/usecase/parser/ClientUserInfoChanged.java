package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.CLIENT_USER_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ClientUserInfoChanged implements ParserGame {

  @Override
  public boolean isThisLine(String line) {
    final Matcher matcher = getMatcher(line);
    return matcher.find();
  }

  private Matcher getMatcher(String line) {
    return buildLinePatternFor(CLIENT_USER_INFO_PATTERN).matcher(line);
  }

  @Override
  public void execute(String line, Game game) {
    String[] split = line.split( "\\\\" );
    String userName = split[1];
    log.info("addPlayers:  " + userName);
    game.addPlayers(userName);
  }

}
