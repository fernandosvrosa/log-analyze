package br.com.log.analyze.domain.usecase;

import br.com.log.analyze.domain.entity.GameLine;
import br.com.log.analyze.domain.entity.GameReferences;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class TurnLinesIntoGames {

  public List<GameLine> execute(Stream<String> lines) {
    List<GameLine> games = new ArrayList<>();
    AtomicInteger matches = new AtomicInteger();
    AtomicReference<GameLine> game = new AtomicReference<>(new GameLine());

    lines.forEach(line -> {

      if (isGameStart(line.trim())) {
        if (matches.get() > 0) {
          games.add(game.get());
          game.set(new GameLine());
        }
        matches.getAndIncrement();
        game.get().setGameName("game: " + matches);

      } else {
        game.get().addLine(line);
      }

    });

    games.add(game.get());

    return games;
  }

  private boolean isGameStart(final String line) {
    return GameReferences.buildLinePatternFor(GameReferences.GAME_START_PATTERN).matcher(line).matches();
  }


}
