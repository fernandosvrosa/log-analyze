package br.com.log.analyze.domain.usecase;

import br.com.log.analyze.domain.dataprovider.GamePersistenceDataProvider;
import br.com.log.analyze.domain.dataprovider.ReadFileDataProvider;
import br.com.log.analyze.domain.entity.GameResult;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StartAnalyse {

  private final ReadFileDataProvider readFileDataProvider;

  private final GamePersistenceDataProvider gamePersistenceDataProvider;

  private final TurnLinesIntoGames turnLinesIntoGames;

  private final GameLineToGame gameLineToGame;

  public Mono<GameResult> execute(String fileName) {

    var lines = readFileDataProvider.getLines(fileName);

    var gameLines = turnLinesIntoGames.execute(lines);

    var games = gameLineToGame.execute(gameLines);

    return gamePersistenceDataProvider
        .save(GameResult.builder().id(UUID.randomUUID().toString()).games(games).build());

  }

}


