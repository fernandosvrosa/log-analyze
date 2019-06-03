package br.com.log.analyze.app.entrypoint;

import br.com.log.analyze.app.dataprovider.mongo.repository.GameModelRepository;
import br.com.log.analyze.app.entrypoint.model.AnalyzeRequest;
import br.com.log.analyze.domain.entity.Game;
import br.com.log.analyze.domain.entity.GameLine;
import br.com.log.analyze.domain.entity.GameResult;
import br.com.log.analyze.domain.usecase.GameLineToGame;
import br.com.log.analyze.domain.usecase.TurnLinesIntoGames;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnalyZeControllerTest {

  @Autowired
  private WebTestClient webTestClient;


  @Autowired
  private GameModelRepository repository;


  @Test
  public void testSuccessAnalyze() {

    AnalyzeRequest request = new AnalyzeRequest();
    request.setName("test.log");

    webTestClient.post().uri("/analyze")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(request), AnalyzeRequest.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.games[0].name").isEqualTo("game: 1")
        .jsonPath("$.games[0].totalKills").isEqualTo(4)
        .jsonPath(".games[0].players").isNotEmpty()
        .jsonPath(".games[0].kills").isNotEmpty();
  }


  @Test
  public void testGetGameResult() {
    List<Game> games = getGames();
    GameResult gameResult = GameResult.builder().id(UUID.randomUUID().toString()).games(games)
        .build();

    GameResult gameSave = repository.save(gameResult).block();

    webTestClient.get().uri("/analyze/{id}", Collections.singletonMap("id", gameSave.getId()))
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.id").isEqualTo(gameSave.getId())
        .jsonPath("$.games[0].name").isEqualTo(gameSave.getGames().get(0).getName())
        .jsonPath("$.games[0].totalKills").isEqualTo(gameSave.getGames().get(0).getTotalKills())
        .jsonPath(".games[0].players").isNotEmpty()
        .jsonPath(".games[0].kills").isNotEmpty();

  }


  @SneakyThrows
  private List<Game> getGames() {
    List<ParserGame> parserGames = Arrays
        .asList(new ClientUserInfoChanged(), new KillPlayer(), new KillWorld(), new NotParser());

    GameLineToGame lineToGame = new GameLineToGame(parserGames);

    List<GameLine> linesGame = getLinesGame();
    return lineToGame.execute(linesGame);
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