package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.PLAYER_KILLER;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;
import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;

@Component
public class KillPlayer implements ParserGame{

  @Override
  public boolean isThisLine(String line) {
    final Matcher matcher = getMatcher(line);
    return matcher.matches();
  }

  private Matcher getMatcher(String line) {
    return buildLinePatternFor(PLAYER_KILLER).matcher(line);
  }

  @Override
  public void execute(String line, Game game) {
    final Matcher matcher = getMatcher(line);
    String playerKilled = matcher.group(3);
    game.addKillPlayer(playerKilled);

  }
}
