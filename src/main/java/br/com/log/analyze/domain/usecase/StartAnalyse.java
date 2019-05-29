package br.com.log.analyze.domain.usecase;

import br.com.log.analyze.domain.entity.Analyse;
import br.com.log.analyze.domain.entity.GameLine;
import br.com.log.analyze.domain.dataprovider.ReadFileDataProvider;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StartAnalyse {

  private final ReadFileDataProvider readFileDataProvider;

  private final TurnLinesIntoGames turnLinesIntoGames;

  private final GameLineToGame gameLineToGame;

  public Mono<Analyse> execute(String fileName) {

    var lines = readFileDataProvider.getLines(fileName);

    var gameLines = turnLinesIntoGames.execute(lines);


    var games = gameLineToGame.execute(gameLines);

    // Persiste games mongoDB

    return null;

  }

}


