package br.com.log.analyze.app.dataprovider.mongo;

import br.com.log.analyze.app.dataprovider.mongo.repository.GameModelRepository;
import br.com.log.analyze.domain.dataprovider.GamePersistenceDataProvider;
import br.com.log.analyze.domain.entity.GameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GamePersistenceProvider implements GamePersistenceDataProvider {

  private final GameModelRepository gameModelRepository;

  @Override
  public Mono<GameResult> save(GameResult gameResult) {
    return gameModelRepository.save(gameResult);
  }

}
