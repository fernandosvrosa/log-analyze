package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.KILL_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.WORLD;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KillWorld implements ParserGame {

  @Override
  public boolean isThisLine(String line) {
    boolean KillWorld = false;
    final Matcher matcher = getMatcher(line);


    if (matcher.find()){
      String firstPlayer = getFirstPlayer(line).getLeft();
      KillWorld = WORLD.equals(firstPlayer);

    }
    return KillWorld;
  }


  @Override
  public void execute(String line, Game game) {
    String playerKilled = getFirstPlayer(line).getRight();
    log.info("removeKillPlayer: " +playerKilled);
    game.removeKillPlayer(playerKilled);

  }


  private Pair<String, String> getFirstPlayer(String line) {
    String[] split = line.split( "killed" );
    String[] splitFirstPlayer = split[0].split( ":" );
    String[] splitSecondPlayer = split[1].split( "by" );
    String firstPlayer = splitFirstPlayer[splitFirstPlayer.length - 1].trim();
    String secondPlayer = splitSecondPlayer[0].trim();
    return new ImmutablePair<>(firstPlayer, secondPlayer);
  }

  private Matcher getMatcher(String line) {
    return buildLinePatternFor(KILL_INFO_PATTERN).matcher(line);
  }


}
