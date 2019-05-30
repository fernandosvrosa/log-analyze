package br.com.log.analyze.domain.usecase;


import br.com.log.analyze.domain.entity.Game;
import br.com.log.analyze.domain.entity.GameLine;
import br.com.log.analyze.domain.usecase.parser.ParserGame;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GameLineToGame {

  private final List<ParserGame> parserGames;


  public List<Game> execute(List<GameLine> gameLines) {

    List<Game> games = gameLines.stream()
        .map(gameLine -> linesToGame(gameLine.getGameName(), gameLine.getLines())).collect(
            Collectors.toList());

    return games;
  }

  @Synchronized
  private Game linesToGame(String gameName, List<String> lines) {
    Game game = Game.builder().name(gameName).build();
    lines.forEach(line -> toGame(line, game));
    return game;


  }


  public void toGame(String line, Game game) {
    parserGames.stream().filter(parserGame -> parserGame.isThisLine(line))
        .findFirst()
        .orElse(null)
        .execute(line, game);
  }


}
