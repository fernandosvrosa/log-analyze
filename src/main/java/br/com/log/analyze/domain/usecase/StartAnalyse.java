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

  public Mono<Analyse> execute(String fileName) {

    Stream<String> lines = readFileDataProvider.getLines(fileName);

    List<GameLine> gameLines = turnLinesIntoGames.execute(lines);

    return null;

  }

}


