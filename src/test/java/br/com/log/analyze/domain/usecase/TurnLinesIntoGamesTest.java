package br.com.log.analyze.domain.usecase;

import br.com.log.analyze.domain.entity.GameLine;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class TurnLinesIntoGamesTest {

  @Test
  public void testTurnLinesIntoGames() throws IOException {
    Stream<String> lines = getLines();
    TurnLinesIntoGames turnLinesIntoGames = new TurnLinesIntoGames();
     List<GameLine> games = turnLinesIntoGames.execute(lines);

    Assert.assertEquals(2, games.size());

  }

  private Stream<String> getLines() throws IOException {
    String resources = getClass().getResource("/log/").getPath();
    Path path = Paths.get(resources, "test.log");
    Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);

    return lines;
  }

}