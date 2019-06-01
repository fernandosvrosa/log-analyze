package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.KILL_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.WORLD;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KillPlayer implements ParserGame{

  @Override
  public boolean isThisLine(String line) {
    boolean killPlayer = false;
    final Matcher matcher = getMatcher(line);


    if (matcher.find()){
      String firstPlayer = getFirstPlayer(line);
      killPlayer = !WORLD.equals(firstPlayer);

    }
    return killPlayer;
  }


  @Override
  public void execute(String line, Game game) {
    String playerKilled = getFirstPlayer(line);
    log.info("addKillPlayer: " +playerKilled);
    game.addKillPlayer(playerKilled);

  }


  private String getFirstPlayer(String line) {
    String[] split = line.split( "killed" );
    String[] splitFirstPlayer = split[0].split( ":" );
    return splitFirstPlayer[splitFirstPlayer.length - 1].trim();
  }

  private Matcher getMatcher(String line) {
    return buildLinePatternFor(KILL_INFO_PATTERN).matcher(line);
  }


}
