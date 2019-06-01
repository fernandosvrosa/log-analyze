package br.com.log.analyze.domain.dataprovider;

import br.com.log.analyze.domain.entity.GameResult;
import reactor.core.publisher.Mono;

public interface GamePersistenceDataProvider {

  Mono<GameResult> save(GameResult gameResult);

}
