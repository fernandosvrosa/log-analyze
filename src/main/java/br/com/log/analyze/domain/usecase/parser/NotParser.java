package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.CLIENT_USER_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.PLAYER_KILLER;
import static br.com.log.analyze.domain.entity.GameReferences.WORLD_KILLER;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;

@Component
public class NotParser  implements ParserGame{

  @Override
  public boolean isThisLine(String line) {
    final Matcher clientUser = buildLinePatternFor(CLIENT_USER_INFO_PATTERN).matcher(line);
    final Matcher killPlayer = buildLinePatternFor(PLAYER_KILLER).matcher(line);
    final Matcher world = buildLinePatternFor(WORLD_KILLER).matcher(line);
    return !killPlayer.matches() && !world.matches();
  }



  @Override
  public void execute(String line, Game game) {

  }
}
