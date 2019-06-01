package br.com.log.analyze.domain.usecase;

import static org.junit.Assert.assertEquals;

import br.com.log.analyze.domain.entity.Game;
import br.com.log.analyze.domain.entity.GameLine;
import br.com.log.analyze.domain.usecase.parser.ClientUserInfoChanged;
import br.com.log.analyze.domain.usecase.parser.KillPlayer;
import br.com.log.analyze.domain.usecase.parser.KillWorld;
import br.com.log.analyze.domain.usecase.parser.NotParser;
import br.com.log.analyze.domain.usecase.parser.ParserGame;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;

public class GameLineToGameTest {


  private final List<ParserGame> parserGames = Arrays
      .asList(new ClientUserInfoChanged(), new KillPlayer(), new KillWorld(), new NotParser());

  @Test
  public void testGameLineToGameSuccess() throws IOException {
    GameLineToGame target = new GameLineToGame(parserGames);

    List<GameLine> linesGame = getLinesGame();
    List<Game> result = target.execute(linesGame);

    assertEquals(linesGame.size(), result.size());
    assertEquals("game: 1", result.get(0).getName());
    assertEquals("game: 2", result.get(1).getName());

  }

  private List<GameLine> getLinesGame() throws IOException {

    Stream<String> lines = getLines();
    TurnLinesIntoGames turnLinesIntoGames = new TurnLinesIntoGames();
    return turnLinesIntoGames.execute(lines);
  }


  private Stream<String> getLines() throws IOException {
    String resources = getClass().getResource("/log/").getPath();
    Path path = Paths.get(resources, "test.log");
    Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);

    return lines;
  }

}