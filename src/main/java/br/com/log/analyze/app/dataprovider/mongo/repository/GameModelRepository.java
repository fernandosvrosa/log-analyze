package br.com.log.analyze.app.dataprovider.mongo.repository;

import br.com.log.analyze.domain.entity.GameResult;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameModelRepository extends ReactiveMongoRepository<GameResult, String> {

}
